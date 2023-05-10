package com.shoppingapp.microservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.shoppingapp.microservice.model.Role;

@EnableMongoRepositories
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(String name);
	boolean existsByName(String name);
}