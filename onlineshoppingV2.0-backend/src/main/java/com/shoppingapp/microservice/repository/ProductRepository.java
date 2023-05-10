package com.shoppingapp.microservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.shoppingapp.microservice.model.Product;

@EnableMongoRepositories
public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> findByProductName(String productName);

	List<Product> findAllByProductName(String productName);

	Boolean existsByProductName(String productName);
}
