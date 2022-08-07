package com.devsuperior.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.catalog.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
