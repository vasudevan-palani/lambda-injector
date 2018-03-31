package com.newtech;

public class Counter {

	int value;
	
	Counter(int value){
		this.value = value;
	}
	
	void increment(int value){
		this.value += value;
	}
}
