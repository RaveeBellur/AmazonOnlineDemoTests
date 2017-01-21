package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.resources.BaseTest;

public class SearchAndOrderProductTest extends BaseTest {

	@Test(dataProvider = "TestData", groups = { "includeTest", "orderProduct", "Smoke" })
	public void testOrderBook(String product) {
		searchPage = getSearchPage();
		Assert.assertTrue(searchPage.searchProduct(product), "Product not found!");
		
		searchResultPage = getSearchResultPage();
		searchResultPage.selectProduct();
		searchResultPage.addToCart();
		
		shoppingCartPage = getShoppingCartPage();
		int cartCount = shoppingCartPage.checkIfProductAddedToCart(); 
		Assert.assertEquals(cartCount, 1, "Expected Count: 1 Found Count:" + cartCount);
		shoppingCartPage.checkout();
		
		signInPage = getSignInPage();
		signInPage.enterUserName();
		
	}

	@DataProvider(name = "TestData")
	public Object[][] createBookSearchData(){
		return new Object[][] {
				{"The Alchemist"},
				//{"Wings of Fire"},
		};
	}
	

	//@Test(groups = { "excludeTest","searchProduct" })
	public void testSearchProduct() {
		// TODO
	}

}
