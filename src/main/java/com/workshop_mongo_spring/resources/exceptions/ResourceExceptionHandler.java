package com.workshop_mongo_spring.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.workshop_mongo_spring.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;


// Classe responsavel por tratar possíveis erros nas requisiçoes
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;				
		StandartError err = new StandartError(System.currentTimeMillis(),status.value(),"Não Encontrado : - ( ",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err); 
	}
	
}
