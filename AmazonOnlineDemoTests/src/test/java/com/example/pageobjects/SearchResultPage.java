package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.resources.PageTitle;
import com.example.util.BasePageObject;
import com.example.util.TimeUnit;

public class SearchResultPage extends BasePageObject {
	
	@FindBy(id = "add-to-cart-button")
	WebElement btnAddToCart;

	final By txtSelectProduct = By.xpath("//li[@id='result_0']//h2"); 

	@Override
	protected void load() {
		System.out.println("load() " + this.getClass().getName());
	}

	@Override
	protected void isLoaded() {
		System.out.println("isLoaded()  " + this.getClass().toString());
		waitUntilLoadedAndPageTitleContains(TimeUnit.SEC_30.getSeconds(),PageTitle.SEARCH_RESULT_PAGE_TITLE.getPageTitle());
		jsWaitForPageToLoad(TimeUnit.SEC_30.getSeconds());
	}

	public void selectProduct() {
		waitUntilLoadedAndElementClickable(txtSelectProduct);
		click(txtSelectProduct);
		waitUntilLoadedAndPageTitleContains(TimeUnit.MIN_1.getSeconds(),PageTitle.PRODUCT_PAGE_TITLE.getPageTitle());
	}

	public void addToCart() {
		waitUntilLoadedAndElementClickable(getBy("btnAddToCart"));
		click(btnAddToCart);
	}
	
}
