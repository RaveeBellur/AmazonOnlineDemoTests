package com.example.util;

import java.io.File;
import org.apache.commons.lang3.StringUtils;

public class FileManager {

	/**
	 * This method returns the path of the screenshots directory within the
	 * workspace. Also creates screenshots directory within the workspace if not
	 * present.
	 * 
	 * @return screenshots directory path.
	 */
	public static String screenshotDirectory() {
		String dir = StringUtils.join(new String[] { System.getProperty("user.dir"), "screenshots" }, File.separator);

		File dirFile = new File(dir);

		if (!new File(dir).exists()) {
			dirFile.mkdir();
		}
		return dir;
	}

}
