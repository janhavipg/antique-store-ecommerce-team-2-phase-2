package com.ecommerce.antique.store.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.entities.ProductTo;
import com.ecommerce.antique.store.entities.Category;

import com.ecommerce.antique.store.services.ProductServices;

import com.ecommerce.antique.store.repository.ProductRepository;
import com.ecommerce.antique.store.repository.CategoryRepository;

import java.util.Optional;
import java.util.NoSuchElementException;

//test basic CRUD operations
//It setup the environment with spring container and beans registered in it
@SpringBootTest
public class ProductServicesTest {
	
	@Autowired
	ProductServices productServices;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Test
	public void getProductBasedOnPage() {
		// not mine
	}
	
	@Test
	public void findAllProducts() {
		// not mine
	}
	
	@Test
	public void addProduct() {
		ProductTo testProductTo = new ProductTo();
		
		testProductTo.setProductname("addProductTestProduct");
		testProductTo.setProductdescription("This product has been added in ProductServicesTest.java");
		testProductTo.setProductimage("assets/test_product.jpg");
		testProductTo.setPrice(BigDecimal.valueOf(12.99));
		testProductTo.setDiscountpercent(25);
		testProductTo.setCategoryid(Long.valueOf(1));
		productServices.addProduct(testProductTo);
		
		Product lastAddedProduct = productServices.findLastProduct();

		Product testProduct = new Product();
		testProduct.setProductname(testProductTo.getProductname());
		testProduct.setProductdescription(testProductTo.getProductdescription());
		testProduct.setProductimage(testProductTo.getProductimage());
		testProduct.setPrice(testProductTo.getPrice());
		testProduct.setDiscountpercent(testProductTo.getDiscountpercent());
		testProduct.setDiscountprice(testProductTo.getPrice().multiply( BigDecimal.valueOf(Float.valueOf(100 - testProduct.getDiscountpercent()) / 100)));

		Category testProductCategory = new Category();
		
		Optional<Category> cateLookup = categoryRepository.findById(testProductTo.getCategoryid());		
		try {
			testProductCategory = cateLookup.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't find categoryId (in addProduct TEST)");
			fail("Couldn't find categoryId " + testProductTo.getCategoryid());
		}
		
		testProduct.setCategory(testProductCategory);
		
		assertSame(testProduct, lastAddedProduct);
		
	}

	
	@Test
	public void setCategoryDiscount() {
		
	}
	
	@Test
	public void delete () {
		
	}
}