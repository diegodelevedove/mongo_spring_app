package com.workshop_mongo_spring.exceptions;

//Se fosse Exception o compilador exigiria que você trate a excessão
//Quando ele tentar fazer alguma coisa vai pedir um try catch também
//Se for runTimeException ele não exige tratamento
//Mas precisa tratar

public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	
	

}
