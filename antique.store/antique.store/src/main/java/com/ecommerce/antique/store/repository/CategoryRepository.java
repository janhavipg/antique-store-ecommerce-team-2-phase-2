package com.ecommerce.antique.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;


import com.ecommerce.antique.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query("SELECT c FROM categories c WHERE c.categoryname = ?1")
	Category searchByName(@Param("name") String name);
}
