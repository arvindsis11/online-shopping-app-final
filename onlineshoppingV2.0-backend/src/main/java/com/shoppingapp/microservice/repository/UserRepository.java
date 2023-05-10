package com.shoppingapp.microservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.shoppingapp.microservice.model.UserDao;

@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserDao, String> {
	Optional<UserDao> findByUsername(String username);

	Optional<UserDao> findByEmailid(String emailid);

	Optional<UserDao> findByUsernameOrEmailid(String username, String emailid);// further use--fix me

	Boolean existsByUsername(String username);

	Boolean existsByEmailid(String emailid);
}