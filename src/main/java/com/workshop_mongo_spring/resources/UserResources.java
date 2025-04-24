package com.workshop_mongo_spring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workshop_mongo_spring.domain.Post;
import com.workshop_mongo_spring.domain.User;
import com.workshop_mongo_spring.dto.UserDto;
import com.workshop_mongo_spring.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	
	@Autowired
	private UserService userService;
	
	/*
	mockando 
	@RequestMapping(method = RequestMethod.GET)	
	//@GetMapping tanto essa como a de cima dão na mesma
	public ResponseEntity<List <User>> findAll(){ // podemos retornar um tipo List mas o ResponseEntity é uma classe mais detalhada de resposta (do ponto de vista do rest) 
		User user1 = new User("1","maria brown","maria@maria.com");
		User user2 = new User("2","alex gree","alex@alex.com");
		List<User> usuarios = new ArrayList<>();
		usuarios.addAll(Arrays.asList(user1,user2)); 
		return ResponseEntity.ok().body(usuarios);
		
		
	}
	*/
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<List <UserDto>> findAll(){ 		
		List<User> usuarios = userService.findAll();
		List<UserDto> usuariosDto = usuarios.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuariosDto);
		
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public ResponseEntity<UserDto> findById(@PathVariable String id){ // pra indicar que o id do anotation é o mesmo que passei no metodo @PathVariable 		
		User usuario = userService.findById(id);		
		return ResponseEntity.ok().body(new UserDto(usuario));
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<Void> userInsert(@RequestBody UserDto userDto){ // pra indicar que o id do anotation é o mesmo que passei no metodo @PathVariable 		
		User usuario = userService.fromDtoToUser(userDto);
		usuario = userService.insertUser(usuario);
	 // Tem uma resposta especifica do spring que pega o novo endereço que foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)	
	public ResponseEntity<Void> deleteById(@PathVariable String id){ // pra indicar que o id do anotation é o mesmo que passei no metodo @PathVariable 		
		userService.deleteUser(id);	// não preciso retornar nada então aqui não precisa ser um User
		return ResponseEntity.noContent().build(); 
		
		
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)	
	public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto , @PathVariable String id){ // pra indicar que o id do anotation é o mesmo que passei no metodo @PathVariable 		
		User usuario = userService.fromDtoToUser(userDto);
		usuario.setId(id);//Precisa setar o id primeiro que chegou, pra garantir que seja o id que veio		
		usuario = userService.updateUser(usuario);	 
		return ResponseEntity.noContent().build(); 
		
	}
	
	

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)	
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){ // pra indicar que o id do anotation é o mesmo que passei no metodo @PathVariable 		
		User usuario = userService.findById(id);		
		return ResponseEntity.ok().body(usuario.getPosts());
		
		
	}
	
	
}
