package com.shoppingapp.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.microservice.model.Product;
import com.shoppingapp.microservice.model.ProductSetDto;
import com.shoppingapp.microservice.service.ProductService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api/v1.0/shopping/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(notes="adding product into database", value="admin allowed")
	public ResponseEntity<?> addProducts(@RequestBody Product product,@RequestHeader("Authorization") String token){
		log.info("add product---");
		return productService.addProduct(product);
		
	}
	
	@GetMapping("/all")
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")//fix--here for frontend--,@RequestHeader("Authorization") String token
	@ApiOperation(notes="checking the authorized user with the help of token", value="admin and users allowed")
	public ResponseEntity<List<Product>> viewAllProducts(){
		log.info("retrieve all the products---");
		return productService.getAllProducts();
	}
	
	@GetMapping("/search/{productName}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@ApiOperation(notes="checking the authorized user with the help of token", value="authorized user")
	public ResponseEntity<?> searchProduct(@PathVariable(value = "productName") String productName,@RequestHeader("Authorization") String token){
		log.info("search product by product name---");
		return productService.searchProduct(productName);
	}
	
	@DeleteMapping("/{productName}/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")//fix
	@ApiOperation(notes="checking the authorized user with the help of token and delete product", value="admin role only allowed")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "productName") String productName,@PathVariable(value = "id") String id,@RequestHeader("Authorization") String token){
		log.info("delete product by product name---");
		return productService.deleteProduct(id,productName);
		
	}
	
	@PatchMapping("/{productName}/update/{id}")//productname==username
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(notes="checking the authorized user with the help of token and update product", value="admin role only allowed")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "productName") String productName,@PathVariable(value = "id") String id,@RequestBody Product product,@RequestHeader("Authorization") String token){
		log.info("update product by product name---");
		return productService.updateProduct(id,productName, product);
		
	}
	
	//to do getmapping for one product
	@GetMapping("/{id}")
	@ApiOperation(notes="testing purpose only", value="testing")
	public ResponseEntity<?> getOneProduct(@PathVariable(value = "id") String id){
		return productService.getOneProduct(id);
		
	}
	
	

}
