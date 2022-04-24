package com.ecommerce.antique.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.antique.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
