package com.ecommerce.antique.store.services;

import java.util.List;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.entities.Category;
import com.ecommerce.antique.store.repository.ProductRepository;
import com.ecommerce.antique.store.repository.CategoryRepository;

import com.ecommerce.antique.store.entities.ProductTo;
import com.ecommerce.antique.store.entities.CategoryDiscount;

import java.util.Optional;
import java.util.NoSuchElementException;

import java.util.List;

@Service
public class ProductServices {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
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
	
	public Product findLastProduct() {
		Product lastProduct = productRepository.findLastProduct();
		return lastProduct;
	}
	
	public boolean delete(Long id) {
		var removed = productRepository.remove(id);
		if(removed > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addProduct(ProductTo productTo) {
		Product newProduct  = new Product();
		BigDecimal price = productTo.getPrice();
		newProduct.setProductname(productTo.getProductname());
		newProduct.setProductdescription(productTo.getProductdescription());
		newProduct.setProductimage(productTo.getProductimage());
		newProduct.setPrice(price);
		int discountPercent = productTo.getDiscountpercent();
		newProduct.setDiscountpercent(discountPercent);
		
		BigDecimal discountPrice = price.multiply( BigDecimal.valueOf(Float.valueOf(100 - discountPercent) / 100));
		discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		newProduct.setDiscountprice(discountPrice);
		
		
		System.out.println(discountPercent + " " +  price );
		Optional<Category> cateLookup = categoryRepository.findById(productTo.getCategoryid());
		Category newProductCategory;
		
		try {
			newProductCategory = cateLookup.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't find categoryId (in addProduct)");
			return false;
		}
		newProduct.setCategory(newProductCategory);
		productRepository.save(newProduct);
	
		return true;
	}
	
	
	
	public boolean updateProduct(ProductTo productTo) {
		Long productId = productTo.getProductid();
		String productName = productTo.getProductname();
		String productDescription = productTo.getProductdescription();
		String productImage = productTo.getProductimage();
		BigDecimal price = productTo.getPrice();
		int discountPercent = productTo.getDiscountpercent();

		Product dbProduct = productRepository.searchByName(productName);
		productId = dbProduct.getProductid();
		Optional<Product> optOriginalProduct = productRepository.findById(productId);
		Product originalProduct;
		
		try {
			originalProduct = optOriginalProduct.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't find productId (in updateProduct)");
			return false;
		}

		
		//Product originalProduct = productRepository.findById(productId);
		
		Category originalProductCategory = originalProduct.getCategory();
		Long orignalCatagoryId = originalProductCategory.getCategoryid();
		Long categoryId = productTo.getCategoryid();
		
		System.out.println('"'+productDescription+'"');

		System.out.println(productName + " " + productDescription + " " + productImage + " " + price + " " + discountPercent);
		if(categoryId == null || categoryId == 0) {
			categoryId = orignalCatagoryId;
		}

		Optional<Category> cateLookup = categoryRepository.findById(categoryId);
		Category newProductCategory;
		
		try {
			newProductCategory = cateLookup.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't find categoryId (in updateProduct)");
			return false;
		}

		if(productName == null) {
			productName = originalProduct.getProductname();
		}
		

		if(price == BigDecimal.valueOf(0.0)){

			price = originalProduct.getPrice();
		}
		
		if(discountPercent == -1) {
			discountPercent = originalProduct.getDiscountpercent();
		}
		
		if(productDescription == "-"){
			System.out.println("qwhujioe");

			productDescription = originalProduct.getProductdescription();
		}
		
		if(productImage == "-"){
			System.out.println("qwhjkle");
			productImage = originalProduct.getProductimage();
		}
		
		if(price == null) {
			price = originalProduct.getPrice();
		}
		
		Product newProduct = new Product();
		System.out.println(price);
		newProduct.setProductid(productId);
		newProduct.setProductname(productName);
		newProduct.setProductdescription(productDescription);
		newProduct.setProductimage(productImage);
		
		newProduct.setCategory(newProductCategory);

		newProduct.setPrice(price);
		newProduct.setDiscountpercent(discountPercent);
		
		BigDecimal discountPrice = price.multiply( BigDecimal.valueOf(Float.valueOf(100 - discountPercent) / 100));
		discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		newProduct.setDiscountprice(discountPrice);
		
		productRepository.save(newProduct);
		return true;
	}
	
	public boolean setCategoryDiscount(CategoryDiscount categoryDiscount) {
		Long categoryId;
		String categoryName = categoryDiscount.getCategoryname();
		int percentDiscount = categoryDiscount.getDiscountpercent();
		
		Category cateLookupByName = categoryRepository.searchByName(categoryName);
		
		categoryId = cateLookupByName.getCategoryid();
		
		System.out.println("categoryId:" + categoryId);

		
		Optional<Category> cateLookup = categoryRepository.findById(categoryId);
		Category category;
				
		try {
			category = cateLookup.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't find categoryId (in setCategoryDiscount)");
			return false;
		}
		
		List<Product> productsList =  productRepository.findByCategory(category);
		Product product;
		BigDecimal price;
		BigDecimal discountPrice;
		for(int i = 0; i < productsList.size(); i++){
			product = productsList.get(i);
			price = product.getPrice();
			
			discountPrice = price.multiply(BigDecimal.valueOf(Float.valueOf(100 - percentDiscount) / 100));
			discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			product.setDiscountprice(discountPrice);
			product.setDiscountpercent(percentDiscount);
			
			productRepository.save(product);
		
		}
		return true;

	}

}
