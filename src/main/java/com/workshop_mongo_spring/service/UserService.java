package com.workshop_mongo_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop_mongo_spring.domain.User;
import com.workshop_mongo_spring.dto.UserDto;
import com.workshop_mongo_spring.exceptions.ObjectNotFoundException;
import com.workshop_mongo_spring.repository.UserRepository;

@Service
public class UserService {
	
	//Injeção automatica de dependencia do spring
	//o service tem os métodos que buscam no repository
	//Usando autowired
	
	
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	public User insertUser(User user) {
		return userRepository.insert(user);
	}
	
	//Poderia ficar na User
	//Mas como vai chamar o banco deixamos aqui
	//Passar um DTO pra user
	public User fromDtoToUser(UserDto userDto)  {
		return new User(userDto.getId(),userDto.getName(),userDto.getEmail());
	}
	
	public void deleteUser(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	//Pra atualizar tem que receber um objeto
	//Precisamos instanciar um novo objeto buscando pelo id
	//chamar um método que vai setar os novos valores
	//salvar a operação	
	public User updateUser(User user){
		User newUser = userRepository.findById(user.getId()).get();		
		updateData(newUser, user);
		return userRepository.save(newUser);
		

	}

	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());	
		
	}
	
}
