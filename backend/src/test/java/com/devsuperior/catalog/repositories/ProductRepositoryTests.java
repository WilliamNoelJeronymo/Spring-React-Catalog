package com.devsuperior.catalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.catalog.entites.Product;
import com.devsuperior.catalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long existingId, nonExistingId, countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void saveShouldPeristWithAutoIncrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		product.setId(null);
		product = repository.save(product);
		Assertions.assertNotNull(product);
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}

	@Test
	public void findByIdShouldReturnOptionalProductWhenIdExist() {
		Optional<Product> result = repository.findById(existingId);

		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalProducWhenIdDoesNotExist() {
		Optional<Product> result = repository.findById(nonExistingId);

		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {

		repository.deleteById(existingId);

		Optional<Product> result = repository.findById(existingId);

		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}
