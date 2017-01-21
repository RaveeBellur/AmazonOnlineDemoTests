package com.example.dataobjects;

public class BookData {
	private String productTitle = "";
	private String author = "";
	private String offerPrice = "";
	private String edition = "";

	@Override
	public String toString() {
		return String.format("Product Title:  " + this.productTitle + "\n" + "Author: " + this.author + "\n"
				+ "Edition:  " + this.edition + "\n" + "Offer Price:  " + this.offerPrice + "\n");
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

}