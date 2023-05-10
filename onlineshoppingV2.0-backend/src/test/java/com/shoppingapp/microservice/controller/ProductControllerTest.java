/**
 * 
 */
package com.shoppingapp.microservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.microservice.model.Product;
import com.shoppingapp.microservice.repository.ProductRepository;
import com.shoppingapp.microservice.service.ProductServiceImpl;

/**
 * @author 2101931
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
class ProductControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository prodRepo;
	
	@MockBean
	private ProductServiceImpl prodService;
	
	private Product prodObj;
	@BeforeEach
	void setup() {
		prodObj = new Product("1", "onePlus", "5G support", 50000, "64px camera", 5, "Hurry up in stock");
	}
	
	/**
	 * Test method for {@link com.shoppingapp.microservice.controller.ProductController#addProducts(com.shoppingapp.microservice.model.Product)}.
	 * @throws Exception 
	 */
	@Test
	void testAddProducts() throws Exception {
		String jsonObj = this.mapToJson(prodObj);
		MvcResult mvresult = mockMvc.perform(post("/api/v1.0/shopping/products/add").header("Authorization", "test")
				.contentType("application/json").content(jsonObj)).andReturn();
		assertThat(mvresult.getResponse().getStatus()).isEqualTo(401);
		
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.controller.ProductController#viewAllProducts()}.
	 * @throws Exception 
	 */
	@Test
	void testViewAllProducts() throws Exception {
		mockMvc.perform(get("/api/v1.0/shopping/products/all")).andExpect(status().isUnauthorized());
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.controller.ProductController#searchProduct(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testSearchProduct() throws Exception {
		
		mockMvc.perform(get("/api/v1.0/shopping/products/search/onPlus")).andExpect(status().isUnauthorized());
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.controller.ProductController#deleteProduct(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testDeleteProduct() throws Exception {
		String jsonObj = this.mapToJson(prodObj);
		MvcResult mvresult = mockMvc.perform(delete("/api/v1.0/shopping/products/onePlus/delete/1").header("Authorization", "test")
				.contentType("application/json").content(jsonObj)).andReturn();
		assertThat(mvresult.getResponse().getStatus()).isEqualTo(401);//fix -- here
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.controller.ProductController#updateProduct(java.lang.String, java.lang.String, com.shoppingapp.microservice.model.ProductSetDto)}.
	 * @throws Exception 
	 */
	@Test
	void testUpdateProduct() throws Exception {
		String jsonObj = this.mapToJson(prodObj);
		MvcResult mvresult = mockMvc.perform(put("/api/v1.0/shopping/products/onePlus/update/1").header("Authorization", "test")
				.contentType("application/json").content(jsonObj)).andReturn();
		assertThat(mvresult.getResponse().getStatus()).isEqualTo(405);//not logged in user
	}
	

	String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}


}
