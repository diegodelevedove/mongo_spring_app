package com.workshop_mongo_spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.workshop_mongo_spring.domain.Post;

//Veja que extends um mongoRepository e não um JPArepository

@Repository
public interface PostRepository extends MongoRepository<Post, String>  {

	//regex query do mongo pesquise depois
	//https://docs.mongodb.com/manual/reference/operator/query/regex/
	//?0 é porque o text pega o primeiro parametro do método - o 'i' 
	//é um parametro que ignora maisculas e minuscula
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }") 
	List<Post> findByTitle(String text);	
	
	//Query Methods, procure no mongo, basta implementar aqui nos repositórios
	//isso vale pro sql também e outras implementações de banco
	List<Post> findByTitleContainingIgnoreCase(String text); 
	//essa função faz com que ele ignore se é maiuscylo ou minusculo na hora de chamar
		
	/*atenção
	 * 
	 * As duas maneiras acima fazem a mesma coisa mude o nome do metodo importado lá no service
	 * 
	 * 
	 */
	
	
	//consulta com vários criterios (Query operators) e Logical query operators
	//Data minima = date : gte é great then or equal ?1 porque o primeiro parametro é 0
	//Data maxima = date : lte é less then or equal ?2 porque o primeiro parametro é 0
	//Perceba que em comments queremos o campo text
	//Perceba que fizemos a query pegar duas datas e os campos title body e comments de um documento 
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text,Date minDate, Date maxDate);
	
	
	

}
