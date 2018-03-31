package com.newtech;

import java.util.HashMap;

import org.apache.nutch.storage.WebPage;

public class Context {
	
	Configuration conf;
	
	private HashMap<String,WebPage> output=null;
	
	Context(){
		output = new HashMap<>();
		conf = new Configuration();
	}

	public Configuration getConfiguration() {
		return this.conf;
	}
	
	public Counter getCounter(String category,String key) {
		return new Counter(this.conf.getInt(category+"."+key,0));
	}

	public void write(String reversedUrl, WebPage row) {
		output.put(reversedUrl,row);
		
	}

	public Object getOutput() {
		return output;
	}

}
