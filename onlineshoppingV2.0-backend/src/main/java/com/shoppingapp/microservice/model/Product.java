package com.shoppingapp.microservice.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model to be used for storing product details")
@Document(collection = "products")
public class Product {

	@Id
	@ApiModelProperty(notes = "for product id")
	private String id;

	@ApiModelProperty(notes = "for product name")
	private String productName;
	
	@ApiModelProperty(notes = "for productDescription")
	private String productDescription;
	
	@ApiModelProperty(notes = "for product price")
	private double price;
	
	@ApiModelProperty(notes = "for product features")
	private String features;
	
	@ApiModelProperty(notes = "for product quantity")
	private int quantity;
	
	@ApiModelProperty(notes = "for product status")
	private String status;

}
