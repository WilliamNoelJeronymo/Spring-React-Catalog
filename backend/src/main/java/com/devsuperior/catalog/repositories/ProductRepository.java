package com.devsuperior.catalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.catalog.entites.Category;
import com.devsuperior.catalog.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
//	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
//			+ "(COALESCE(:category) IS NULL OR :category IN cats) AND "
//			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')))")
//	Page<Product> find(Category category, Pageable pageable, String name);
	
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
	        + "(COALESCE(:category, NULL) IS NULL OR cats = :category) AND "
	        + "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')))")
	Page<Product> find(@Param("category") Category category,
	                   @Param("name") String name,
	                   Pageable pageable);
}
