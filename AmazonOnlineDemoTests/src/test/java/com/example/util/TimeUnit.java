package com.example.util;

public enum TimeUnit {
	SEC_1(1), SEC_3(3), SEC_5(5), SEC_10(10), SEC_15(15), SEC_30(30), MIN_1(60), MIN_2(2 *60);

	private int seconds;

	private TimeUnit(int seconds) {
		this.seconds = seconds;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMilliSeconds() {
		return seconds * 1000;
	}
}