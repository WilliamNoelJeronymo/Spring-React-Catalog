package com.devsuperior.catalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
	
	public EntityNotFoundException(String msg) {
		super(msg);	
	}

}
