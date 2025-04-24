package com.workshop_mongo_spring.dto;

import java.io.Serializable;
import java.util.Objects;

import com.workshop_mongo_spring.domain.User;

// Dto não é persistida por si mesma, ela so é uma parte do que vamos persistir no objeto principal

public class AuthorDto implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private String id;
	private String name;	
	
	public AuthorDto() {
		
	}

	public AuthorDto(User user) {
		id = user.getId();
		name = user.getName();
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	//*** Não implementar hashCode nem HashEquals nos DTOS ***
	

	
	
}
