package com.example.resources;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.example.util.DriverInitializer;

public class BaseTest extends BaseSteps {
	DriverInitializer driverInitializer;
	
	@BeforeSuite(alwaysRun = true) 
	public static void init() {
		Reporter.log("Starting the set up.....", true);
		
		if(System.getProperty("url") == null)
			System.setProperty("url", PropertyFileReader.getProperty("url"));
		if(System.getProperty("browserType") == null)
			System.setProperty("browserType", PropertyFileReader.getProperty("browserType"));
		
		System.setProperty("driverLibDir", System.getProperty("user.dir").concat("/lib"));
	}
	
	
	@BeforeMethod(alwaysRun = true) 
	public void setUp() throws Exception {
		Reporter.log("Loading browser.....", true);
		driverInitializer = new DriverInitializer();
		DriverInitializer.getAppropriateDriver(System.getProperty("browserType"),System.getProperty("driverLibDir"));
		driverInitializer.triggerURL(System.getProperty("url"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		if (PropertyFileReader.flagSet("closeBrowserAfterTest")) {
			Reporter.log("Closing down.....", true);
			driverInitializer.closeAllBrowsers();
		}
	}



}
