package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.resources.PageTitle;
import com.example.util.BasePageObject;
import com.example.util.TimeUnit;

public class ShoppingCartPage extends BasePageObject {
	
	@FindBy(id = "hlb-ptc-btn-native")
	WebElement btnCheckout;
	
	final By txtCartCount = By.id("nav-cart-count"); 

	@Override
	protected void load() {
		System.out.println("load() " + this.getClass().getName());
	}

	@Override
	protected void isLoaded() {
		System.out.println("isLoaded()  " + this.getClass().toString());	
		waitUntilLoadedAndPageTitleContains(TimeUnit.SEC_30.getSeconds(),PageTitle.SHOPPING_CART_PAGE_TITLE.getPageTitle());
		jsWaitForPageToLoad(TimeUnit.SEC_30.getSeconds());
	}
	
	public int checkIfProductAddedToCart() {
		String count = getText(txtCartCount);
		return Integer.parseInt(count);
	}
	
	public void checkout() {
		waitUntilLoadedAndElementClickable(getBy("btnCheckout"));
		click(btnCheckout);
	}
}
