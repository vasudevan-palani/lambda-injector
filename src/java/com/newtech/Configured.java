package com.newtech;

public class Configured implements Configurable {
	private Configuration conf;
	
	public Configured() {
		this.conf = new Configuration();
	}
	
	public Configured(Configuration conf2) {
		this.conf = conf2;
	}

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
		
	}
	@Override
	public Configuration getConf() {
		return this.conf;
	}
	
}
