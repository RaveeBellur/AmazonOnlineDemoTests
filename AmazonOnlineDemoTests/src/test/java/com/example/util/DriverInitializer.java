package com.example.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.testng.Reporter;

public class DriverInitializer {
	/** The WebDriver object */
	public static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver getAppropriateDriver(String browserType, String location) throws Exception {
		// WebDriver driver = null;
		if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", location + "/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", location + "/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("IE")) {
			File file;
			if (System.getProperty("sun.arch.data.model").equals("32"))
				file = new File(location + "/IEDriverServer32/IEDriverServer.exe");
			else
				file = new File(location + "/IEDriverServer.exe");
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver(capability);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	/**
	 * This function triggers the URL in the browser
	 * 
	 * @param URL
	 *            - The web URL
	 */
	public void triggerURL(String url) {
		if (driver != null) {
			try {
				driver.get(url);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * This method closes all the browser windows opened in the current test
	 * session
	 */
	public void closeAllBrowsers() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void takeScreenShot() {
		Reporter.log("Taking Screenshot..", true);

		File file = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			java.util.Date date = new java.util.Date();
			String timestamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss").format(date.getTime());
			timestamp = timestamp.replace(":", "_");

			String fileName = StringUtils.join(new String[] { FileManager.screenshotDirectory(), timestamp + "_"
						+ getClass().getSimpleName() + ".png" }, File.separator);

			FileUtils.copyFile(file, new File(fileName));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
