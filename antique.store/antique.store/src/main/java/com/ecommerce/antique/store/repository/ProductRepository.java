package com.ecommerce.antique.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.ecommerce.antique.store.entities.Product;
import com.ecommerce.antique.store.entities.Category;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	// custom queries will be added
		// query by JPA : select * from Product where name = ?1(positional parameter);
		//public Product findByName(String productname);
	
		Optional<Product> findByProductid(long productid);
		
//		List<Product> findByNameOrDescription(String productname, String description);
//		List<Product> findByNameAndDescription(String productname, String description);
//		Product findDisctinctByName(String name);
//		List<Product> findByPriceGreaterThan(BigDecimal price);
//		List<Product> findByPriceLessThan(BigDecimal price);
//		List<Product> findByNameContaining(String name);
//		List<Product> findByNameLike(String name);
//		List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
//		List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
//	    List<Product> findByNameIn(List<String> names);
//		List<Product> findFirst2ByOrderByNameAsc();
//		List<Product> findTop2ByOrderByPriceDesc();
//		
//		// define JPQL query using @Query annotation with index or position parameter
//		@Query("select p from Product p where p.name=?1 or p.description=?2")
//		Product findByNameorDescriptionJPQLNamedParam(@Param("name") String name, @Param("description") String description);
	//	
//		//define JPQL query using @Query annotation with named param
//		@Query("select * from Product p where p.name = :name or p.description = :description")
//		Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name, @Param("description") String description);
	//	
//		// define Native SQL query using @Query annotation with index or position param
//		@Query(value="select * from products p where p.name = ?1 or p.description = ?2", nativeQuery = true)
//		Product findByNameOrDescriptionSQLIndex(String name, String descrption);
	//	
//		// define Native SQL query using @Query annotation with Named Parameter
//		@Query(value= "select * from products p where p.name = :name or p.description = :description", nativeQuery = true)
//		Product findByNameOrDescriptionSQLNamedParam(@Param("name") String name, String description);
	//	
//		// define named JPQL query
//		Product findByPrice(@Param("price") BigDecimal price);
	//	
//		List<Product> findAllOrderByNameDesc();
		
		// define named native SQL query
//		@Query(nativeQuery = true)
//		Product findByDescription(@Param("description") String description);
	//	
//		List<Product> findAllOrderByNameASC();
	//	
		@Transactional
		@Modifying
		@Query("DELETE FROM Product WHERE productid = ?1")
		int remove(@Param("id") Long id);
		/*
		@Query(value = "SELECT * FROM product WHERE categoryid = ?1", nativeQuery = true)
		List<Product> getProductsFromCategoryId(@Param("categoryid") Long categoryId);
		*/
		
		@Transactional
		@Modifying
		@Query("SELECT p FROM Product p WHERE p.productid = (SELECT MAX(p.productid) FROM Product)")
		Product findLastProduct();
		
     	@Query(nativeQuery = true)
		List<Product> findByCategory(Category category);

    	
    	@Query("SELECT p FROM Product p WHERE p.productname = ?1")
    	Product searchByName(@Param("name") String name);
    
}
