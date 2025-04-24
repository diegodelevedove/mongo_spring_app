package com.workshop_mongo_spring.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop_mongo_spring.domain.Post;
import com.workshop_mongo_spring.service.PostService;
import com.workshop_mongo_spring.util.URL;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {

	@Autowired
	private PostService postService;

	// pra indicar que o id do anotation é o mesmo
	// que passei no metodo @PathVariable
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findPostById(@PathVariable String id) { 
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);

	}
	
	//o text é o que vamos repassar pra url e o dafaultValue é uma string vazia
	//caso não tenha nenhuma	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) { 
		text = URL.decodeParam(text); // decodifica o texto - tem que criar uma classe pra fazer esse trabalho
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);

		// vá na url de posts/titlesearch?text= o texto que existe em algum post
		// http://localhost:8080/posts/titlesearch?text=m%20dia
	}

	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) 
	{
		
		text = URL.decodeParam(text); // decodifica o texto
		Date min = URL.convertDate(minDate, new Date(0L)); // se não tiver data atribui uma data padrão la de 1970 no
															// new Date()
		Date max = URL.convertDate(maxDate, new Date()); // se não tiver data ou problema na hora de converter atribui
															// uma data padrão la de 1970 no new Date()

		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	//pra testar esse endpoint acesse
	//http://localhost:8080/posts/fullsearch?text=bom&maxDate=2018-03-30
	//com texto
	//http://localhost:8080/posts/fullsearch?text=aproveite&maxDate=2018-03-30
	//com data minima
	//http://localhost:8080/posts/fullsearch?text=aproveite&minDate=2018-03-30&maxDate=2018-03-31
}
