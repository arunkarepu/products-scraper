package com.scraper.productscraper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scraper.productscraper.models.ProductModel;
import com.scraper.productscraper.services.ScraperService;



@RestController
public class ScraperController {
	@Autowired
	private ScraperService scraperService;
	
	@GetMapping("/review/{id}")
	public ProductModel getProductDetails(@PathVariable String id) throws Exception {
		return scraperService.getProductDetailsById(id);
	}
}
