/**
 * 
 */
package com.shoppingapp.microservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingapp.microservice.model.Product;

/**
 * @author 2101931
 *
 */
@DataJpaTest
class ProductRepositoryTest {

	
	@MockBean
	ProductRepository repo;
	
	private Product product;
	
	@BeforeEach
	void setup() {
		product = new Product("1", "oneplus", "5G support", 50000, "testing", 5, "in stock");
	}
	
	@Test
	void testFindByProductName() {
		
		when(repo.findByProductName("oneplus")).thenReturn(Optional.of(product));
		assertThat(repo.findByProductName("oneplus")).isEqualTo(Optional.of(product));
		
		
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.repository.ProductRepository#findAllByProductName(java.lang.String)}.
	 */
	@Test
	void testFindAllByProductName() {
		when(repo.findAllByProductName("oneplus")).thenReturn(Stream.of(product,new Product("2", "oneplus", "5G support", 50000, "testing", 5, "in stock")).collect(Collectors.toList()));
		assertThat(repo.findAllByProductName("oneplus").size()).isEqualTo(2);
	}

	/**
	 * Test method for {@link com.shoppingapp.microservice.repository.ProductRepository#existsByProductName(java.lang.String)}.
	 */
	@Test
	void testExistsByProductName() {
		when(repo.existsByProductName("oneplus")).thenReturn(true);
		assertThat(repo.existsByProductName("oneplus")).isEqualTo(true);
	}

}
