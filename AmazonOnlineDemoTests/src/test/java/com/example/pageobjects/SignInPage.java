package com.example.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.resources.PageTitle;
import com.example.resources.PropertyFileReader;
import com.example.util.BasePageObject;
import com.example.util.TimeKeeper;
import com.example.util.TimeUnit;

public class SignInPage extends BasePageObject {

	@FindBy(id = "ap_email")
	WebElement txtUserName;

	@FindBy(id = "ap_password")
	WebElement txtPassword;

	@FindBy(id = "signInSubmit")
	WebElement btnLogin;

	@Override
	protected void load() {
		System.out.println("load() " + this.getClass().getName());
	}

	@Override
	protected void isLoaded() {
		System.out.println("isLoaded()  " + this.getClass().toString());
		waitUntilLoadedAndPageTitleContains(TimeUnit.SEC_30.getSeconds(),PageTitle.SIGN_IN_PAGE_TITLE.getPageTitle());
		jsWaitForPageToLoad(TimeUnit.SEC_30.getSeconds());
	}
	
	public void enterUserName() {
		type(PropertyFileReader.getProperty("userName"), txtUserName);
		TimeKeeper.waitInSeconds(TimeUnit.SEC_3.getSeconds());
	}

	public void enterPassword() {
		type("", txtUserName);
	}

	public void clickSignInButton() {
		click(btnLogin);
	}

}
