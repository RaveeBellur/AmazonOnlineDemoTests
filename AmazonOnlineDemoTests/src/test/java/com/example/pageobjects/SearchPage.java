package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.example.resources.PageTitle;
import com.example.util.BasePageObject;
import com.example.util.TimeUnit;

public class SearchPage extends BasePageObject {

	@FindBy(id = "twotabsearchtextbox")
	WebElement txtSearchBox;

	final By txtSearchResult = By.id("issDiv2"); 
	
	@Override
	protected void load() {
		System.out.println("load() " + this.getClass().getName());
	}

	@Override
	protected void isLoaded() throws Error {
		System.out.println("isLoaded()  " + this.getClass().toString());
		waitUntilLoadedAndPageTitleContains(TimeUnit.MIN_1.getSeconds(),PageTitle.SEARCH_PAGE_TITLE.getPageTitle());
		waitUntilElementPresent(getBy("txtSearchBox"));
	}
	
	public boolean searchProduct(String product) {
		type(product, txtSearchBox);
		waitUntilLoadedAndElementClickable(txtSearchResult);
		boolean status = isDisplayed(txtSearchResult);
		click(txtSearchResult);
		return status;
	}
	
	

}
