package com.shoppingapp.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetDto {
	
	private String productName;
	
	private String productStatus;
	
	private int productQuantity;

}
