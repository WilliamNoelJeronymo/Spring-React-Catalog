package com.devsuperior.catalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.catalog.entites.Category;

@RestController
@RequestMapping(value = "/categories")
public class CadegoryResource {
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = new ArrayList<>();
		list.add(new Category (1L,"Books"));
		list.add(new Category (2L,"Eletronics"));
		return ResponseEntity.ok().body(list);
	}
}
