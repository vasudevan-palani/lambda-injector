package com.newtech;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.nutch.net.URLFilters;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.scoring.ScoringFilterException;
import org.apache.nutch.scoring.ScoringFilters;
import org.apache.nutch.storage.Mark;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.TableUtil;

public class UrlMapper {
	private URLNormalizers urlNormalizers;
	private int interval;
	private float scoreInjected;
	private URLFilters filters;
	private ScoringFilters scfilters;
	private long curTime;
	
	private static final String YES_STRING = "y";
	
	private static final Logger LOG = Logger.getLogger(UrlMapper.class);

	/** metadata key reserved for setting a custom score for a specific URL */
	public static String nutchScoreMDName = "nutch.score";
	/**
	 * metadata key reserved for setting a custom fetchInterval for a specific URL
	 */
	public static String nutchFetchIntervalMDName = "nutch.fetchInterval";

	protected void setup(Context context) {
		urlNormalizers = new URLNormalizers(context.getConfiguration(), URLNormalizers.SCOPE_INJECT);
		interval = context.getConfiguration().getInt("db.fetch.interval.default", 2592000);
		filters = new URLFilters(context.getConfiguration());
		scfilters = new ScoringFilters(context.getConfiguration());
		scoreInjected = context.getConfiguration().getFloat("db.score.injected", 1.0f);
		curTime = context.getConfiguration().getLong("injector.current.time", System.currentTimeMillis());
	}

	protected void map(String value, Context context) throws IOException, InterruptedException {
		String url = value.toString().trim(); // value is line of text

		if (url != null && (url.length() == 0 || url.startsWith("#"))) {
			/* Ignore line that start with # */
			return;
		}

		// if tabs : metadata that could be stored
		// must be name=value and separated by \t
		float customScore = -1f;
		int customInterval = interval;
		Map<String, String> metadata = new TreeMap<String, String>();
		if (url.indexOf("\t") != -1) {
			String[] splits = url.split("\t");
			url = splits[0];
			for (int s = 1; s < splits.length; s++) {
				// find separation between name and value
				int indexEquals = splits[s].indexOf("=");
				if (indexEquals == -1) {
					// skip anything without a =
					continue;
				}
				String metaname = splits[s].substring(0, indexEquals);
				String metavalue = splits[s].substring(indexEquals + 1);
				if (metaname.equals(nutchScoreMDName)) {
					try {
						customScore = Float.parseFloat(metavalue);
					} catch (NumberFormatException nfe) {
					}
				} else if (metaname.equals(nutchFetchIntervalMDName)) {
					try {
						customInterval = Integer.parseInt(metavalue);
					} catch (NumberFormatException nfe) {
					}
				} else
					metadata.put(metaname, metavalue);
			}
		}
		try {
			url = urlNormalizers.normalize(url, URLNormalizers.SCOPE_INJECT);
			url = filters.filter(url); // filter the url
		} catch (Exception e) {
			LOG.warn("Skipping " + url + ":" + e);
			url = null;
		}
		if (url == null) {
			context.getCounter("injector", "urls_filtered").increment(1);
			return;
		} else { // if it passes
			String reversedUrl = TableUtil.reverseUrl(url); // collect it
			WebPage row = new WebPage();
			row.setFetchTime(curTime);
			row.setFetchInterval(customInterval);

			// now add the metadata
			Iterator<String> keysIter = metadata.keySet().iterator();
			while (keysIter.hasNext()) {
				String keymd = keysIter.next();
				String valuemd = metadata.get(keymd);
				row.getMetadata().put(keymd, ByteBuffer.wrap(valuemd.getBytes()));
			}

			if (customScore != -1)
				row.setScore(customScore);
			else
				row.setScore(scoreInjected);

			try {
				scfilters.injectedScore(url, row);
			} catch (ScoringFilterException e) {
					LOG.warn(
							"Cannot filter injected score for url " + url + ", using default (" + e.getMessage() + ")");
			}
			context.getCounter("injector", "urls_injected").increment(1);
			//row.getMarkers().put(DbUpdaterJob.DISTANCE, String.valueOf(0));
			Mark.INJECT_MARK.putMark(row, YES_STRING);
			context.write(reversedUrl, row);
		}
	}
}
