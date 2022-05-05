package com.ecommerce.antique.store.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.antique.store.entities.Product;

//test basic CRUD operations
//It setup the environment with spring container and beans registered in it
@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		assertTrue(true);
	}
	/*
//	@Test
//	public void saveProductObject() {
//		// create product
//     Product product = new Product();
//     product.setProductname("Toy");
//     product.setProductdescription("It's a toy");
//     product.setPrice(new BigDecimal(100));
//     product.setProductimage("toy.jpg");
//     
//     // save product
//     Product savedObject = productRepository.save(product);
//
//     // display product info
//     System.out.println(savedObject.getProductid());
//     System.out.println(savedObject.toString());
//
//	}
	
 @Test
 void updateUsingSaveMethod(){

     // find or retrieve an entity by id
     Long id = 1L;
     Product product = productRepository.findById(id).get();

     // update entity information
     product.setProductname("updated product 1");
     product.setProductdescription("updated product 1 desc");

     // save updated entity
     productRepository.save(product);
 }

 @Test
 void findByIdMethod(){
     Long id = 1L;

     Product product = productRepository.findById(id).get();
 }

// @Test
// void saveAllMethod() {
// 	// create method
// 	Product product = new Product();
// 	product.setProductname("Chocolate");
// 	product.setProductdescription("This is a Chocolate");
// 	product.setPrice(new BigDecimal(50));
// 	product.setProductimage("Chocolate.jpg");
// 	
// 	// create method
// 	Product product1 = new Product();
// 	product1.setProductname("LED Light");
// 	product1.setProductdescription("This is a LED Light");
// 	product1.setPrice(new BigDecimal(45));
// 	product1.setProductimage("light.jpg");
// 	
// 	// create method
// 	Product product2 = new Product();
// 	product1.setProductname("Mobile");
// 	product1.setProductdescription("This is a Mobile");
// 	product1.setPrice(new BigDecimal(45));
// 	product1.setProductimage("mobile.jpg");
// }
// 
 @Test
 void findAllMethod() {
 	//query -: select * from product;
 	List<Product> product = productRepository.findAll();
 	
 	product.forEach((p)->{
 		System.out.println(p.getProductname());
 		System.out.println(p.getProductid());
 	});
 }
// 
// @Test
// void deleteByIdMethod() {
// 	// delete record by id
// 	Long id = 1L;
// 	productRepository.deleteById(id);
// }
 
 @Test
 void deleteMethod() {
 	
 	//find an entity by id
 	Long id = 4L;
 	Product product = productRepository.getById(id);
 	
 	// delete(entity)
 	productRepository.delete(product);
 }
 
 @Test
 void deleteAllMethod() {
 	// productRepository.delete();
 	Product product = productRepository.findById(1L).get();
 	Product product1 = productRepository.findById(1L).get();
 	
 	productRepository.deleteAll(List.of(product, product1));
 }
 
 @Test
 void existByIdMethod() {
 	Long id = 7L;
 	
 	boolean result = productRepository.existsById(id);
 	System.out.println(result);
 }
 
 @Test
 void countMethod() {
 	long count = productRepository.count();
 	System.out.println(count);
 }*/
	
	
	
 @Test
 void deleteMethod() {
	 Long id = 1L;
	 int result = productRepository.remove(id);
	 
	 assertTrue(result == 1);
 }
}

