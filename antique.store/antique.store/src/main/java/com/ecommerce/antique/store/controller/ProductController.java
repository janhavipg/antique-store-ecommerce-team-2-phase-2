package com.ecommerce.antique.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.entities.ProductTo;
import com.ecommerce.antique.store.entities.CategoryDiscount;

import com.ecommerce.antique.store.services.ProductServices;

import org.json.simple.JSONObject;

@RestController
@RequestMapping("/api/v2")
public class ProductController {

	private ProductServices productService;
	
	public ProductController(@Autowired ProductServices service) {
		this.productService = service;
	}
	
		// this URL will read data from query parameters
		// query param are appended at the end of URL after
		// http://localhost:8080/page/products?pgnum=0&size=2
//		@GetMapping(value = "/page/products",produces = "application/json")
//		public List<Product> getProductsOnPage(@RequestParam("pgnum") int pageNumber, @RequestParam("size") int pageSize) {
//			List<Product> product = this.productService.getProductBasedOnPage(pageNumber, pageSize);
//			return product;
//		}
		
		@GetMapping("/products")
		public ResponseEntity<List<Product>> findAllProducts(){
			List<Product> product = productService.findAllProducts();
			return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		}
		
		@PostMapping("/products")
		public ResponseEntity<String> addNewProduct(@RequestBody ProductTo productTo ){
			boolean complete = productService.addProduct(productTo);
		     if(complete) {
		    	 return new ResponseEntity<String> (HttpStatus.OK);
		     }
		     else {
		    	 return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		     }		
		}
		
		@PatchMapping("/products")
		public ResponseEntity<String> updateProduct(@RequestBody ProductTo productTo){
			boolean success = productService.updateProduct(productTo);
		    if(success) {
		    	return new ResponseEntity<String> (HttpStatus.OK);
		    }
		    else {
		    	return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		    }			
		}

		public static class idReq {
		    @JsonProperty("id")
		    private long id;
		    
		    public Long getId() {
		    	return this.id;
		    }
		    
		    public void setId(long id) {
		    	this.id = id;
		    }
		}
		
		@DeleteMapping("/products")
		public ResponseEntity<String> deleteProduct(@RequestBody idReq req){
		     boolean complete = productService.delete(req.getId());
		     if(complete) {
		    	 return new ResponseEntity<String> (HttpStatus.OK);
		     }
		     else {
		    	 return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		     }
		}
		
		
		@PostMapping("/products/categoryDiscount")
		public ResponseEntity<String> categoryDiscount(@RequestBody CategoryDiscount categoryDiscount){
			
			boolean complete = productService.setCategoryDiscount(categoryDiscount);
		     if(complete) {
		    	 return new ResponseEntity<String> (HttpStatus.OK);
		     }
		     else {
		    	 return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
		     }
		}
		
		/*
		@UpdateMapping("/products")
		public ResponseEntity<Product> updateProduct(@RequestBody Product product){
			
		}*/
}
