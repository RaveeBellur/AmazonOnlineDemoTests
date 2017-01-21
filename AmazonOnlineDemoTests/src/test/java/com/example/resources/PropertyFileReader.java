package com.example.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyFileReader {

	private static Properties properties = new Properties();

	static {
		try {
			System.out.println("Starting to load properties: "	+ System.getProperty("user.dir"));
			properties.load(new FileInputStream(System.getProperty("user.dir") + "\\config.properties"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	public static boolean flagSet(String key) {
		System.out.println(properties.get(key));
		return properties.get(key).equals("true");
	}
}