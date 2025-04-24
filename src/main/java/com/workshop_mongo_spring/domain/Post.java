package com.workshop_mongo_spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.workshop_mongo_spring.dto.AuthorDto;
import com.workshop_mongo_spring.dto.CommentDto;

@Document //Essa anotação é o equivalente ao @Entity do sql
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	//No mongo o id é um hash por isso tem que ser String
	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	//private User author; foram criados DTOS
	//São classes que podem ser usadas pra representar uma classe concreta ou 
	//Apenas parte de uma classe que vai integrar uma classe concreta, os objetos em DTO não são persistidos
	//por si mesmos, eles integram as classes concretas.	
	//(outra alternativa é usar comands e representations)
	//Lembrando que os comands são para transação de dados e representation pra retorno de dados.
	private AuthorDto author;	
	
	private List<CommentDto> comments = new ArrayList<>();
	
	public Post() {
		
	}

	public Post(String id, Date date, String title, String body,AuthorDto author) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.author = author;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}
	
	public AuthorDto getAuthor() {
		return author;
	}


	public void setAuthor(AuthorDto author) {
		this.author = author;
	}
	
	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
	
}
