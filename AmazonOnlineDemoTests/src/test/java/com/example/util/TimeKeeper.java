package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeKeeper {

	/** The date format.This is used for Logging and Reporting purpose */
	private static String DATE_FORMAT_NOW = "yyyy-MM-dd";

	/** The time format.This is used for Logging and Reporting purpose */
	private static String TIME_FORMAT_NOW = "HH:mm:ss";

	/**
	 * This method instructs the current execution flow to wait for a specified
	 * time mentioned in the argument.
	 * 
	 * @param int
	 *            - number of minutes to wait.
	 */
	public static void waitInMinutes(int min) {
		try {
			Thread.sleep(min * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method instructs the current execution flow to wait for a specified
	 * time mentioned in the argument.
	 * 
	 * @param int
	 *            - number of seconds to wait.
	 */
	public static void waitInSeconds(int seconds) {
		int millisec = seconds * 1000;
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets the current system time and returns it.
	 * 
	 * @return String - the current system time.
	 */
	public static String timeNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	/**
	 * This method gets the current system date and returns it.
	 * 
	 * @return String - the current system date.
	 */
	public static String dateNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());

	}

}
