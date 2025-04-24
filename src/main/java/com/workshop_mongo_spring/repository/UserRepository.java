package com.workshop_mongo_spring.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workshop_mongo_spring.domain.User;

//Veja que extends um mongoRepository e não um JPArepository

@Repository
public interface UserRepository extends MongoRepository<User, String>  {

}
