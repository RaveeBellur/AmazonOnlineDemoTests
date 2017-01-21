package com.example.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;


public abstract class BasePageObject extends LoadableComponent<BasePageObject> {
	final static Logger logger = LoggerFactory.getLogger(BasePageObject.class);

	/**
	 * General use constants.
	 */
	public final static int SECONDS_15 = 15;
	public final static int SECONDS_30 = 30;

	public WebDriver driver;

	public BasePageObject() {
		this.driver = DriverInitializer.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void waitUntilLoadedAndElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, SECONDS_30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitUntilLoadedAndPageTitleContains(int timeout, String pageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}

	public void waitUntilElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, SECONDS_30);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitUntilElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, SECONDS_30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void jsWaitForPageToLoad(int timeOutInSeconds) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "return document.readyState";

		// Check the readyState before doing any waits
		if (js.executeScript(command).toString().equals("complete")) {
			logger.debug("waitForPageToLoad() - JS document state " + js.executeScript(command).toString());
			return;
		}

		for (int i = 0; i < timeOutInSeconds; i++) {
			TimeKeeper.waitInSeconds(2);
			if (js.executeScript(command).toString().equals("complete")) {
				logger.debug("waitForPageToLoad() - JS document state after " + timeOutInSeconds + " secs:"
						+ js.executeScript(command).toString());
				break;
			}
		}
	}
	
	public By getBy(String fieldName) {
		try {
			return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
		} catch (NoSuchFieldException e) {
			return null;
		}
	}

	public void click(By byLocator) {
		WebElement element = findElement(byLocator);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.click();
	}

	/**
	 * Clicks the element passed in. If the element is null, an error is logged
	 * and the test will continue.
	 * 
	 * @param byLocator
	 */
	public void click(WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement();
			return;
		}
		element.click();
	}

	/**
	 * Clicks the element found via the byLocator. This method will continue
	 * looking for the element up to the maxWaitTime. If no element is found
	 * within that time, an error is logged and the test will continue.
	 * 
	 * @param byLocator
	 * @param maxWaitTime
	 *            - In seconds
	 */
	public void click(By byLocator, int maxWaitTime) {
		WebElement element = findElement(byLocator, maxWaitTime);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return;
		}
		element.click();
	}

	public void type(String inputText, WebElement element) {
		if (element == null) {
			logErrorForNotFindingElement(element);
			return;
		}
		element.sendKeys(inputText);
	}

	/**
	 * Returns the text value of an element via the byLocator. If the element
	 * can not be found immediately an error is logged and the test will
	 * continue.
	 * 
	 * @param byLocator
	 * @return
	 */
	public String getText(By byLocator) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if (element == null) {
			logErrorForNotFindingElement(byLocator);
			return "";
		}
		return element.getText();
	}
	
	/**
	 * Returns if an element via the byLocator is visibly present on the page at
	 * this exact point in time.
	 * 
	 * @param byLocator
	 * @return
	 */
	public boolean isDisplayed(By byLocator) {
		return isElementCurrentlyDisplayed(byLocator);
	}
	
	/**
	 * Returns an element found via the byLocator. The element must already be
	 * present and enabled on the page before this method is called. If you'd
	 * like to keep looking for the element for a length of time call
	 * findElement(byLocator, maxWaitTime); If no element is found, null will be
	 * returned.
	 * 
	 * @param byLocator
	 * @return - The WebElement object if found, or null if not found
	 */
	public WebElement findElement(By byLocator) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		WebElement element;
		if (!isElementEnabledWithinWait(byLocator, 0)) {
			return null;
		}
		element = driver.findElement(byLocator);
		return element;
	}
	
	/**
	 * Looks for an element via the byLocator for up to maxWaitTime. As soon as
	 * an element is present and enabled matching the byLocator it is returned.
	 * If no match is found within the maxWaitTime, null is returned.
	 * 
	 * @param byLocator
	 * @param maxWaitTime
	 *            - In seconds
	 * @return
	 */
	public WebElement findElement(By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		WebElement element;
		if (!isElementEnabledWithinWait(byLocator, maxWaitTime)) {
			return null;
		}
		element = driver.findElement(byLocator);
		return element;
	}
	
	private boolean isElementEnabledWithinWait(By byLocator, int maxWaitTime) {
		if (isWaitForSuccessful(ExpectedConditions.elementToBeClickable(byLocator), maxWaitTime)) {
			return true;
		}
		return false;
	}

	private boolean isWaitForSuccessful(ExpectedCondition<WebElement> condition, Integer maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		if (maxWaitTime == null) {
			maxWaitTime = 3;
		}
		WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
		try {
			wait.until(condition);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	private boolean isElementCurrentlyDisplayed(By byLocator) {
		WebElement element = findElementThatIsPresent(byLocator, 0);
		if ((element != null) && element.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Looks for an element via the byLocator for up to maxWaitTime. As soon as
	 * an element is present within the DOM matching the byLocator it is
	 * returned. NOTE: The element does NOT have to be visible for this method.
	 * If no match is found within the maxWaitTime, null is returned.
	 * 
	 * @param byLocator
	 * @param maxWaitTime
	 *            - In seconds
	 * @return
	 * 
	 */
	public WebElement findElementThatIsPresent(final By byLocator, int maxWaitTime) {
		if (driver == null) {
			throwNullPointerExeptionForNullDriver();
		}
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(maxWaitTime, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS);

		try {
			return wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriver) {
					List<WebElement> elems = driver.findElements(byLocator);
					if (elems.size() > 0) {
						return elems.get(0);
					} else {
						return null;
					}
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
	
	protected void logErrorForNotFindingElement(By byLocator) {
		logger.error("Could not find element based on locator: " + byLocator.toString());
	}

	protected void logErrorForNotFindingElement(WebElement element) {
		logger.error("Could not find element based on locator: " + getBy("element").toString());
	}

	protected void logErrorForNotFindingElement() {
		logger.error("Element is null and can not be acted upon");
	}

	protected void throwNullPointerExeptionForNullDriver() {
		throw new NullPointerException(
				"The Driver object you are using is null.  Please make sure you are passing the correct driver instance into the PageObject.");
	}
}
