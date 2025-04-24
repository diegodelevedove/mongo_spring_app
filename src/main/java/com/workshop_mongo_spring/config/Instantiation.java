package com.workshop_mongo_spring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshop_mongo_spring.domain.Post;
import com.workshop_mongo_spring.domain.User;
import com.workshop_mongo_spring.dto.AuthorDto;
import com.workshop_mongo_spring.dto.CommentDto;
import com.workshop_mongo_spring.repository.PostRepository;
import com.workshop_mongo_spring.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();//deletando antes e depois inserindo
		postRepository.deleteAll();//deletando antes e depois inserindo
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//Nesse caso tem que salvar o user primeiro senão ele não cria o id
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		//Usando com user aninhado,veja depois estratégias de como criar documentos no mongo
		Post post = new Post(null,sdf.parse("21/03/2018"),"partiu viagem","vou viajar pra são paulo abraços!!!",new AuthorDto(maria));
		Post post2 = new Post(null,sdf.parse("21/03/2018"),"Bom dia","Acordei feliz hoje",new AuthorDto(maria));
			
		CommentDto c1 = new CommentDto("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDto(alex));
		CommentDto c2 = new CommentDto("Aproveite", sdf.parse("22/03/2018"), new AuthorDto(bob));
		CommentDto c3 = new CommentDto("Tenha um ótimo dia", sdf.parse("23/03/2018"), new AuthorDto(alex));
		
		//Agora associar os comentarios com os authores
		
		post.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post,post2));
		
		//Incluindo os posts em maria ou no objeto dela
		//Lembrando que isso é uma referência que será salva em maria
		//uma espécie de tabela chave ou tabelas de referência 
		maria.getPosts().addAll(Arrays.asList(post,post2));
		userRepository.save(maria);
		
	}

}
