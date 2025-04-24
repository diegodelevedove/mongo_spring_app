package com.workshop_mongo_spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop_mongo_spring.domain.Post;
import com.workshop_mongo_spring.exceptions.ObjectNotFoundException;
import com.workshop_mongo_spring.repository.PostRepository;

@Service
public class PostService {
	
	//Injeção automatica de dependencia do spring
	//o service tem os métodos que buscam no repository
	
	
	@Autowired
	private PostRepository postRepository;

	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));		
	
	}

	//apenas mudar o nome do metodo importado do repository
	public List<Post> findByTitle(String text){
		return postRepository.findByTitleContainingIgnoreCase(text);
		
	}
	
	//A data é medida em milisegundo(instante) um numero grande até a meia noite do dia anterior
	//Pra isso precisamos somar mais um na data
	public List<Post> fullSearch(String text,Date minDate,Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return  postRepository.fullSearch(text, minDate, maxDate);
			
	}
	
}
