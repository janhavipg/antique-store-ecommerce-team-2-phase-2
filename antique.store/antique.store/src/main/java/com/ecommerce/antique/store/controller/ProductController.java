package com.ecommerce.antique.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.services.ProductServices;

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
		public ResponseEntity<Product> addNewOrder(@RequestBody Product product ){
			Product savedProduct =productService.newProduct(product);
			return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
		}
}
