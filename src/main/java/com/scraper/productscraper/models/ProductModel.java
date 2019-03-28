package com.scraper.productscraper.models;

import java.util.List;

public class ProductModel {
	private String productName;
	private String rating;
	private String reviewCount;
	private List<ReviewModel> reviews;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}
	public List<ReviewModel> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}
}
