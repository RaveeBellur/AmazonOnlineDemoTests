package com.example.resources;

public enum PageTitle {
	
	SIGN_IN_PAGE_TITLE("Amazon Sign In"),
	SEARCH_PAGE_TITLE("Online Shopping"),
	SEARCH_RESULT_PAGE_TITLE("Amazon.in"),
	PRODUCT_PAGE_TITLE("Buy"),
	SHOPPING_CART_PAGE_TITLE("Amazon.in Shopping Cart");

	private final String pageTitle;
 	
	private PageTitle (String pageTitle) {
		this.pageTitle =  pageTitle;
	}

	public String getPageTitle() {
		return pageTitle;
	}
	
	public static PageTitle fromPropertyName(String x) throws Exception {
		for (PageTitle currentType : PageTitle.values()) {
	      if (x.equals(currentType.getPageTitle())) {
	        return currentType;
	      }
	    }
	    throw new Exception("Unmatched Type: " + x);
	  }
	
}

