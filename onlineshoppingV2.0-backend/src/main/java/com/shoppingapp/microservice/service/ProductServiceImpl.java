package com.shoppingapp.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoppingapp.microservice.model.Product;
import com.shoppingapp.microservice.model.ProductSetDto;
import com.shoppingapp.microservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	
	private static final String PRODUCT_NOTEXISTS="product does not exists!";

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		log.info("inside getAll method");//fix it in every methods
		return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> searchProduct(String productName) {
		log.info("inside searchproduct method");
		if (productRepo.existsByProductName(productName))
			return new ResponseEntity<>(productRepo.findAllByProductName(productName), HttpStatus.OK);
		return new ResponseEntity<>(PRODUCT_NOTEXISTS, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> addProduct(Product product) {

		log.info("inside add product method");
		if(product.getQuantity()>0) {
			product.setStatus("HURRY UP TO PURCHASE");	
		}
		else
		{
			product.setStatus("OUT OF STOCK");	
		}
		return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
	}

//for admin only
	@Override
	public ResponseEntity<?> updateProduct(String id, String productName,Product product) {
		log.info("inside update product method");
		if (productRepo.existsById(id)) {
			Optional<Product> oldProduct = productRepo.findById(id);
			if(oldProduct.isPresent()) {
				oldProduct.get().setProductName(product.getProductName());
				oldProduct.get().setProductDescription(product.getProductDescription());
				oldProduct.get().setPrice(product.getPrice());
				oldProduct.get().setFeatures(product.getFeatures());
				oldProduct.get().setQuantity(product.getQuantity());
				oldProduct.get().setStatus(product.getStatus());
				return new ResponseEntity<>(productRepo.save(oldProduct.get()), HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>(PRODUCT_NOTEXISTS, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> deleteProduct(String id, String productName) {
		log.info("inside delete all method");
		if (productRepo.existsById(id)||productRepo.existsByProductName(productName)) {
			productRepo.deleteById(id);
			return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(PRODUCT_NOTEXISTS, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> getOneProduct(String id) {
		return new ResponseEntity<>((productRepo.findById(id)), HttpStatus.OK);
	}

}
