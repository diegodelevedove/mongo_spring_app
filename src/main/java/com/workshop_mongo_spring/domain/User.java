package com.workshop_mongo_spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


//Veja que a anotação do jpa é do @Entity aqui é document
@Document(collection = "user")
public class User implements Serializable {
	//Afim de trafegar na rede os dados precisam ser serializados (transformados em bytes)
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;

	// Anotation que referencia uma coleção com outra(não tabelas e sim documentos) 
	//o lazy quer dizer que os posts só serão carregados quando você acessa-los
	//e não apenas quando acessar usuario
	@DBRef(lazy = true) 
	List<Post> posts = new ArrayList<>();
	
	public User(){
		
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	
	//Em ambos os casos (noSql ou SQL) existe a importância de
	// criar um hashCode(id) e um equals afim de diferenciar as classes no momento
	// de usar ou evitar colisão eventual em algum processo.

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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
		
	
}
