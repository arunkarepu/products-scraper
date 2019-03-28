package com.scraper.productscraper.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.scraper.productscraper.models.ProductModel;
import com.scraper.productscraper.models.ReviewModel;



@Service
public class ScraperService {
	private final String amazonUrl = "https://www.amazon.in";

	public ProductModel getProductDetailsById(String id) throws Exception {
		String url = amazonUrl + "/dp/" + id;
		System.out.println(url);
		Document doc = getDocument(url);

		if (doc == null) {
			throw new Exception("Not able to fetch document");
		}

		ProductModel model = new ProductModel();

		Element content = doc.getElementById("centerCol");
		if (content == null) {
			throw new Exception("Not able to fetch document");
		}

		Elements children = content.children();
		model.setProductName(extractProductName(children));
		model.setReviewCount(getReviewsCount(children));
		model.setRating(getStarRating(children));
		model.setReviews(getSampleReviews(doc));

		//This would fetch all review url and get top 10 reviews from it
		
		/*String allReviewsUrl = getAllReviewsUrl(doc);
		Document d2 = getDocument(allReviewsUrl);
		model.setReviews(getAllSampleReviews(d2));*/

		return model;
	}

	private Document getDocument(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(5000).get();
		return doc;
	}

	private String extractProductName(Elements children) {
		String productName = "";

		for (Element child : children) {
			Element s = child.getElementById("title");
			if (s != null) {
				productName = s.text();
				break;
			}
		}
		System.out.println("Product name: " + productName);
		return productName;
	}

	private String getReviewsCount(Elements children) {
		String countStr = "";
		for (Element child : children) {
			Element s = child.getElementById("acrCustomerReviewText");
			if (s != null) {
				countStr = s.text();
				break;
			}
		}

		String count = countStr.split(" ")[0];
		System.out.println("Review count: " + count);
		return count;
	}

	private String getStarRating(Elements children) {
		String starCount = "";
		for (Element child : children) {
			Element s = child.getElementById("acrPopover");
			if (s != null) {
				starCount = s.text();
				break;
			}
		}

		System.out.println("Star rating count: " + starCount);
		return starCount;
	}

	private List<ReviewModel> getSampleReviews(Document doc) {
		try {
			Element content = doc.getElementById("cm-cr-dp-review-list");
			if (content == null) {
				System.out.println("No reviews yet");
				return null;
			}

			Elements divs = content.children();
			System.out.println(divs.size());
			List<ReviewModel> reviews = new ArrayList<>();
			for (Element ele : divs) {
				ReviewModel review = new ReviewModel();
				String name = ele.getElementsByClass("a-profile-name") != null
						? ele.getElementsByClass("a-profile-name").text()
						: "";
				review.setName(name);
				System.out.println(name);

				String rating = ele.getElementsByClass("a-icon-alt") != null
						? ele.getElementsByClass("a-icon-alt").text()
						: "";
				review.setRating(rating);
				System.out.println(rating);

				String date = ele.select("span[data-hook=review-date]") != null
						? ele.select("span[data-hook=review-date]").first().text()
						: "";
				review.setDateOfReview(date);
				System.out.println(date);

				String isVerifiedPurchase = ele.select("span[data-hook=avp-badge-linkless]") != null
						? ele.select("span[data-hook=avp-badge-linkless]").first().text()
						: "";
				review.setPurchaseVerifed(isVerifiedPurchase.isEmpty() ? false : true);
				System.out.println(isVerifiedPurchase);

				String subject = ele.select("a[data-hook=review-title]") != null
						? ele.select("a[data-hook=review-title]").first().text()
						: "";
				review.setSubject(subject);
				System.out.println(subject);

				Element rev = ele.select("span[data-hook=review-body]") != null
						? ele.select("span[data-hook=review-body]").first()
						: null;
				String body = rev.select("div[data-hook=review-collapsed]") != null
						? rev.select("div[data-hook=review-collapsed]").text()
						: "";
				review.setReviewBody(body);
				System.out.println(body);

				reviews.add(review);
			}

			return reviews;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<ReviewModel> getAllSampleReviews(Document doc) {
		try {
			Elements content = doc.select("div[data-hook=review]") ;
			if (content == null) {
				System.out.println("No reviews yet");
				return null;
			}

			/*Elements divs = content.;
			System.out.println(divs.size());*/
			List<ReviewModel> reviews = new ArrayList<>();
			for (Element ele : content) {
				ReviewModel review = new ReviewModel();
				String name = ele.getElementsByClass("a-profile-name") != null
						? ele.getElementsByClass("a-profile-name").text()
						: "";
				review.setName(name);
				System.out.println(name);

				String rating = ele.getElementsByClass("a-icon-alt") != null
						? ele.getElementsByClass("a-icon-alt").text()
						: "";
				review.setRating(rating);
				System.out.println(rating);

				String date = ele.select("span[data-hook=review-date]") != null
						? ele.select("span[data-hook=review-date]").first().text()
						: "";
				review.setDateOfReview(date);
				System.out.println(date);

				String isVerifiedPurchase = ele.select("span[data-hook=avp-badge]") != null
						? ele.select("span[data-hook=avp-badge]").first().text()
						: "";
				review.setPurchaseVerifed(isVerifiedPurchase.isEmpty() ? false : true);
				System.out.println(isVerifiedPurchase);

				String subject = ele.select("a[data-hook=review-title]") != null
						? ele.select("a[data-hook=review-title]").first().text()
						: "";
				review.setSubject(subject);
				System.out.println(subject);

				// Element rev = ele.select("span[data-hook=review-body]") != null ?
				// ele.select("span[data-hook=review-body]").first() : null;
				String body = ele.select("span[data-hook=review-body]") != null
						? ele.select("span[data-hook=review-body]").text()
						: "";
				review.setReviewBody(body);
				System.out.println(body);

				reviews.add(review);
			}

			return reviews;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAllReviewsUrl(Document doc) {
		Element allReviewsFooter = doc.getElementById("reviews-medley-footer");
		if (allReviewsFooter == null) {
			return "";
		}

		String reviewsUrl = allReviewsFooter.select("[data-hook=see-all-reviews-link-foot]") != null
				? allReviewsFooter.select("[data-hook=see-all-reviews-link-foot]").attr("href")
				: "";
		System.out.println("All reviews url: " + amazonUrl + reviewsUrl);
		return amazonUrl + reviewsUrl;
	}
}
