package com.devsuperior.catalog.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devsuperior.catalog.dto.ProductDTO;
import com.devsuperior.catalog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	
	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L ;
	}
	
	@Test
	public void uptdateShoudReturnProductDTOWhenIdExists() throws Exception {
		
		ProductDTO productDTO = Factory.createProductDto();
		
		String expectedName = productDTO.getName();
		String expectedDescription= productDTO.getDescription();

		String jsonBody = objectMapper.writeValueAsString(productDTO);

		ResultActions result = mockMvc.perform(put("/products/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").value(expectedName));
		result.andExpect(jsonPath("$.description").value(expectedDescription));
	}
	
	@Test
	public void uptdateShoudReturnEmptyWhenIdDoesNotExist() throws Exception {
		
		ProductDTO productDTO = Factory.createProductDto();
		
		String jsonBody = objectMapper.writeValueAsString(productDTO);

		ResultActions result = mockMvc.perform(put("/products/{id}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllShouldReturnSortedPageWhenSortByyName() throws Exception{
		ResultActions result = mockMvc.perform(get("/products?page=0&size=12&sort=name,asc")
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalProducts));
	}
	
}
