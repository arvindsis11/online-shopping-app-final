/**
 * 
 */
package com.shoppingapp.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingapp.microservice.model.Product;
import com.shoppingapp.microservice.model.ProductSetDto;
import com.shoppingapp.microservice.repository.ProductRepository;

/**
 * @author 2101931
 *
 */
@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	ProductServiceImpl service;
	
	@MockBean
	ProductRepository repo;
	
	private Product product;
	
	@BeforeEach
	void setUp() throws Exception {
		product  = new Product("1", "Mobile", "oneplus", 50000, "5G support", 5, "in stock");
		
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.service.ProductServiceImpl#getAllProducts()}.
	 */
	@Test
	void testGetAllProducts() {
		Product product1 = new Product("1", "Mobile", "oneplus", 50000, "5G support", 5, "in stock");
		when(repo.findAll()).thenReturn(Stream.of(product1).collect(Collectors.toList()));
		assertThat(service.getAllProducts()).isNotNull();
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.service.ProductServiceImpl#searchProduct(java.lang.String)}.
	 */
	@Test
	void testSearchProduct() {
		when(repo.findAllByProductName("Mobile")).thenReturn(Stream.of(product).collect(Collectors.toList()));
		assertThat(service.searchProduct("Mobile")).isNotNull();
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.service.ProductServiceImpl#addProduct(com.shoppingapp.microservice.model.Product)}.
	 */
	@Test
	void testAddProduct() {
		
		when(repo.save(product)).thenReturn(product);
		assertThat(service.addProduct(product)).isNotNull();
		verify(repo,Mockito.times(1)).save(product);
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.service.ProductServiceImpl#updateProduct(java.lang.String, java.lang.String, com.shoppingapp.microservice.model.ProductSetDto)}.
	 */
	@Test
	void testUpdateProduct() {
		when(repo.existsById("1")).thenReturn(true);
		when(repo.findById("1")).thenReturn(Optional.of(product));
		assertThat(service.updateProduct("1", "mobilr", new Product("mobilr", "HURRY UP", "test", 50000, "test", 4, "test"))).isNotNull();
		verify(repo,Mockito.times(1)).save(product);
		
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.service.ProductServiceImpl#deleteProduct(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testDeleteProduct() {
		when(repo.existsById("1")).thenReturn(true);
		assertThat(service.deleteProduct("1", "mobilr")).isNotNull();
		
	}

}
