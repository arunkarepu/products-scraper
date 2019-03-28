package com.scraper.productscraper.models;

public class ReviewModel {
	private String name;
	private String subject;
	private String dateOfReview;
	private String rating;
	private boolean isPurchaseVerifed;
	private String reviewBody;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDateOfReview() {
		return dateOfReview;
	}
	public void setDateOfReview(String dateOfReview) {
		this.dateOfReview = dateOfReview;
	}
	public boolean isPurchaseVerifed() {
		return isPurchaseVerifed;
	}
	public void setPurchaseVerifed(boolean isPurchaseVerifed) {
		this.isPurchaseVerifed = isPurchaseVerifed;
	}
	public String getReviewBody() {
		return reviewBody;
	}
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}
