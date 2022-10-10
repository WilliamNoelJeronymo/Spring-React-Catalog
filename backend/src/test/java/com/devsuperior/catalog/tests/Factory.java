package com.devsuperior.catalog.tests;

import java.time.Instant;

import com.devsuperior.catalog.dto.ProductDTO;
import com.devsuperior.catalog.entites.Category;
import com.devsuperior.catalog.entites.Product;

public class Factory {
	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "Good phone",800.0 ,"Img.com", Instant.parse("2020-05-09T03:00:00Z"));
		product.getCartegories().add(createCategory());
		return product;
	}
	
	public static ProductDTO createProductDto() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCartegories());
	}
	
	public static Category createCategory() {
		return new Category(2L, "Eletronics");
	}
}
