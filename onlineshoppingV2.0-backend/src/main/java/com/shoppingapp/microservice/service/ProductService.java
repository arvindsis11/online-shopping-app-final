package com.shoppingapp.microservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shoppingapp.microservice.model.Product;

public interface ProductService {
	
	public ResponseEntity<List<Product>> getAllProducts();
	
	public ResponseEntity<?> searchProduct(String productName);
	
	public ResponseEntity<?> addProduct(Product product);
	
	public ResponseEntity<?> updateProduct(String id,String productName,Product product);
	
	public ResponseEntity<?> deleteProduct(String id,String productName);
	
	public ResponseEntity<?> getOneProduct(String id);

}
