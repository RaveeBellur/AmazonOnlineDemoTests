package com.example.resources;

import com.example.pageobjects.SearchPage;
import com.example.pageobjects.SearchResultPage;
import com.example.pageobjects.ShoppingCartPage;
import com.example.pageobjects.SignInPage;

public class BaseSteps {

	public SearchPage searchPage;
	public SearchResultPage searchResultPage;
	public ShoppingCartPage shoppingCartPage;
	public SignInPage signInPage;
	
	public SearchResultPage getSearchResultPage() {
		return (SearchResultPage) new SearchResultPage().get();
	}
	
	public SearchPage getSearchPage() {
		return (SearchPage) new SearchPage().get();
	}
	
	public ShoppingCartPage getShoppingCartPage() {
		return (ShoppingCartPage) new ShoppingCartPage().get();
	}
	
	public SignInPage getSignInPage() {
		return (SignInPage) new SignInPage().get();
	}
} 