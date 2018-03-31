package com.newtech;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class Configuration {

	public static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

	private HashMap<String, String> map = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8703102781031326946L;

	private Configuration conf;

	public Configuration(Configuration conf) {
		this.setMap(conf.getMap());
	}

	public Configuration() {
		this.map = new HashMap<String, String>();
		this.map.put("store.ip.address", "false");
		this.map.put("file.content.limit", "65536");
		this.map.put("file.crawl.redirect_noncanonical", "true");
		this.map.put("file.content.ignored", "true");
		this.map.put("file.crawl.parent", "true");
		this.map.put("http.agent.name", "");
		this.map.put("http.robots.agents", "");
		this.map.put("http.robots.403.allow", "true");
		this.map.put("http.agent.description", "");
		this.map.put("http.agent.url", "");
		this.map.put("http.agent.email", "");
		this.map.put("http.agent.version", "Nutch-2.3.1");
		this.map.put("http.agent.rotate", "false");
		this.map.put("http.agent.rotate.file", "agents.txt");
		this.map.put("http.agent.host", "");
		this.map.put("http.timeout", "10000");
		this.map.put("http.max.delays", "100");
		this.map.put("http.content.limit", "65536");
		this.map.put("http.proxy.host", "");
		this.map.put("http.proxy.port", "");
		this.map.put("http.proxy.username", "");
		this.map.put("http.proxy.password", "");
		this.map.put("http.proxy.realm", "");
		this.map.put("http.auth.file", "httpclient-auth.xml");
		this.map.put("http.verbose", "false");
		this.map.put("http.useHttp11", "false");
		this.map.put("http.accept.language", "en-us,en-gb,en;q=0.7,*;q=0.3");
		this.map.put("http.accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		this.map.put("http.store.responsetime", "true");
		this.map.put("ftp.username", "anonymous");
		this.map.put("ftp.password", "anonymous@example.com");
		this.map.put("ftp.content.limit", "65536");
		this.map.put("ftp.timeout", "60000");
		this.map.put("ftp.server.timeout", "100000");
		this.map.put("ftp.keep.connection", "false");
		this.map.put("ftp.follow.talk", "false");
		this.map.put("db.fetch.interval.default", "2592000");
		this.map.put("db.fetch.interval.max", "7776000");
		this.map.put("db.fetch.schedule.class", "org.apache.nutch.crawl.DefaultFetchSchedule");
		this.map.put("db.fetch.schedule.adaptive.inc_rate", "0.4");
		this.map.put("db.fetch.schedule.adaptive.dec_rate", "0.2");
		this.map.put("db.fetch.schedule.adaptive.min_interval", "60");
		this.map.put("db.fetch.schedule.adaptive.max_interval", "31536000");
		this.map.put("db.fetch.schedule.adaptive.sync_delta", "true");
		this.map.put("db.fetch.schedule.adaptive.sync_delta_rate", "0.3");
		this.map.put("db.update.additions.allowed", "true");
		this.map.put("db.update.max.inlinks", "10000");
		this.map.put("db.ignore.internal.links", "true");
		this.map.put("db.ignore.external.links", "false");
		this.map.put("db.score.injected", "1.0");
		this.map.put("db.score.link.external", "1.0");
		this.map.put("db.score.link.internal", "1.0");
		this.map.put("db.score.count.filtered", "false");
		this.map.put("db.max.outlinks.per.page", "100");
		this.map.put("db.max.anchor.length", "100");
		this.map.put("db.parsemeta.to.crawldb", "");
		this.map.put("db.fetch.retry.max", "3");
		this.map.put("db.signature.class", "org.apache.nutch.crawl.MD5Signature");
		this.map.put("db.signature.text_profile.min_token_len", "2");
		this.map.put("db.signature.text_profile.quant_rate", "0.01");
		this.map.put("generate.max.count", "-1");
		this.map.put("generate.max.distance", "-1");
		this.map.put("generate.count.mode", "host");
		this.map.put("generate.update.crawldb", "false");
		this.map.put("partition.url.mode", "byHost");
		this.map.put("crawl.gen.delay", "604800000");
		this.map.put("fetcher.server.delay", "5.0");
		this.map.put("fetcher.server.min.delay", "0.0");
		this.map.put("fetcher.max.crawl.delay", "30");
		this.map.put("fetcher.threads.fetch", "10");
		this.map.put("fetcher.threads.per.queue", "1");
		this.map.put("fetcher.queue.mode", "byHost");
		this.map.put("fetcher.queue.use.host.settings", "false");
		this.map.put("fetcher.verbose", "false");
		this.map.put("fetcher.parse", "false");
		this.map.put("fetcher.store.content", "true");
		this.map.put("fetcher.timelimit.mins", "-1");
		this.map.put("fetcher.max.exceptions.per.queue", "-1");
		this.map.put("fetcher.throughput.threshold.pages", "-1");
		this.map.put("fetcher.throughput.threshold.sequence", "5");
		this.map.put("fetcher.throughput.threshold.check.after", "5");
		this.map.put("fetcher.queue.depth.multiplier", "50");
		this.map.put("indexingfilter.order", "");
		this.map.put("indexer.score.power", "0.5");
		this.map.put("indexer.max.title.length", "100");
		this.map.put("moreIndexingFilter.indexMimeTypeParts", "true");
		this.map.put("anchorIndexingFilter.deduplicate", "false");
		this.map.put("urlnormalizer.order",
				"org.apache.nutch.net.urlnormalizer.basic.BasicURLNormalizer org.apache.nutch.net.urlnormalizer.regex.RegexURLNormalizer");
		this.map.put("urlnormalizer.regex.file", "./conf/regex-normalize.xml");
		this.map.put("urlnormalizer.loop.count", "1");
		this.map.put("mime.types.file", "tika-mimetypes.xml");
		this.map.put("mime.type.magic", "true");
		this.map.put("plugin.folders", "plugins");
		this.map.put("plugin.auto-activation", "true");
		this.map.put("plugin.includes",
				"protocol-http|urlfilter-regex|parse-(html|tika)|index-(basic|anchor)|urlnormalizer-(pass|regex|basic)|scoring-opic");
		this.map.put("plugin.excludes", "");
		this.map.put("parse.plugin.file", "parse-plugins.xml");
		this.map.put("parser.character.encoding.default", "windows-1252");
		this.map.put("encodingdetector.charset.min.confidence", "-1");
		this.map.put("parser.caching.forbidden.policy", "content");
		this.map.put("parser.html.impl", "neko");
		this.map.put("parser.html.form.use_action", "false");
		this.map.put("parser.html.outlinks.ignore_tags", "");
		this.map.put("htmlparsefilter.order", "");
		this.map.put("parser.timeout", "30");
		this.map.put("parser.skip.truncated", "true");
		this.map.put("tika.htmlmapper.classname", "org.apache.tika.parser.html.IdentityHtmlMapper");
		this.map.put("urlfilter.tld.length", "");
		this.map.put("urlfilter.domain.file", "./conf/domain-urlfilter.txt");
		this.map.put("urlfilter.regex.file", "./conf/regex-urlfilter.txt");
		this.map.put("urlfilter.automaton.file", "./conf/automaton-urlfilter.txt");
		this.map.put("urlfilter.prefix.file", "./conf/prefix-urlfilter.txt");
		this.map.put("urlfilter.suffix.file", "./conf/suffix-urlfilter.txt");
		this.map.put("urlfilter.order", "");
		this.map.put("scoring.filter.order", "");
		this.map.put("lang.ngram.min.length", "1");
		this.map.put("lang.ngram.max.length", "4");
		this.map.put("lang.analyze.max.length", "2048");
		this.map.put("lang.extraction.policy", "detect,identify");
		this.map.put("lang.identification.only.certain", "false");
		this.map.put("index.metadata", "description,keywords");
		this.map.put("metatags.names", "*");
		this.map.put("hadoop.job.history.user.location", "${hadoop.log.dir}/history/user");
		this.map.put("io.serializations",
				"org.apache.hadoop.io.serializer.WritableSerialization,org.apache.hadoop.io.serializer.JavaSerialization");
		this.map.put("solr.mapping.file", "solrindex-mapping.xml");
		this.map.put("solr.commit.size", "250");
		this.map.put("solr.commit.index", "true");
		this.map.put("solr.auth", "false");
		this.map.put("elastic.host", "");
		this.map.put("elastic.port", "9300");
		this.map.put("elastic.cluster", "");
		this.map.put("elastic.index", "nutch");
		this.map.put("elastic.max.bulk.docs", "250");
		this.map.put("elastic.max.bulk.size", "2500500");
		this.map.put("storage.data.store.class", "org.apache.gora.memory.store.MemStore");
		this.map.put("storage.schema.webpage", "webpage");
		this.map.put("storage.schema.host", "host");
		this.map.put("storage.crawl.id", "");
		this.map.put("gora.buffer.read.limit", "10000");
		this.map.put("gora.buffer.write.limit", "10000");

	}

	public void set(String key, String value) {
		this.map.put(key, value);
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public boolean getBoolean(String key, boolean b) {
		return this.map.get(key) != null ? Boolean.parseBoolean(this.map.get(key)) : b;
	}

	public String[] getStrings(String key) {
		return this.get(key, "").split(" ");
	}

	public String get(String key) {
		return this.map.get(key) != null ? this.map.get(key) : null;
	}

	public String get(String key, String defaultVal) {
		return this.map.get(key) != null ? this.map.get(key) : defaultVal;
	}

	public int getInt(String key, int i) {
		return this.map.get(key) != null ? Integer.parseInt(this.map.get(key)) : i;
	}

	public float getFloat(String key, float f) {
		return this.map.get(key) != null ? Float.parseFloat(this.map.get(key)) : f;
	}

	public long getLong(String key, long l) {
		return this.map.get(key) != null ? Long.parseLong(this.map.get(key)) : l;
	}

	public Document getConfResourceAsInputStream(String customMimeTypeFile) {
		// TODO Auto-generated method stub
		return null;
	}

	private ClassLoader classLoader;
	{
		classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = Configuration.class.getClassLoader();
		}
	}

	public URL getResource(String name) {
		return classLoader.getResource(name);
	}

	public Reader getConfResourceAsReader(String name) {
		try {
			URL url = getResource(name);

			if (url == null) {
				LOG.info(name + " not found");
				return null;
			} else {
				LOG.info("found resource " + name + " at " + url);
			}
			LOG.debug("getConfResourceAsReader : "+name);
			return new InputStreamReader(new FileInputStream(new File(name)), Charsets.UTF_8);
		} catch (Exception e) {
			return null;
		}
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

}
