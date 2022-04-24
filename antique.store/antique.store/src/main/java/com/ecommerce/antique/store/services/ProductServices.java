package com.ecommerce.antique.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.repository.ProductRepository;

@Service
public class ProductServices {

	@Autowired
	ProductRepository productRepository;
	
	// pagination 
	public List<Product> getProductBasedOnPage(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> page = productRepository.findAll(pageable);
		List<Product> product = page.getContent();
		if(product.isEmpty()) {
			int oldPageNumber = pageNumber -1;
			pageable = PageRequest.of(oldPageNumber, pageSize);
			page = productRepository.findAll(pageable);
			product = page.getContent();
			
		}
		return product;
	}
	
	// Query -: select * from products
	public List<Product> findAllProducts(){
		List<Product> productList = productRepository.findAll();
		return productList;
	}
	
	// persist order object along with shipping order
	// this method will be called with POST request
	public Product newProduct(Product product) {
		Product newProduct = productRepository.save(product);
		return newProduct;
	}
}
