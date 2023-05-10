/**
 * 
 */
package com.shoppingapp.microservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 2101931
 *
 */
@SpringBootTest
class ProductDaoDtoTest {
    private Product product;
    private ProductSetDto prodset;
    private
	@Test
	void testProductDao() {
		product = new Product();
		product  = new Product("1", "oneplus", "5G", 50000, "test", 5, "In stock");
		product.setFeatures("test");
		product.setId("1");
		product.setPrice(50000);
		product.setProductName("oneplus");
		product.setQuantity(5);
		product.setStatus("in stock");
		product.getFeatures();
		product.getPrice();
		product.getId();
		product.setProductDescription("5g support");
		product.getProductDescription();
		assertThat(product.getFeatures()).isEqualTo("test");
		assertThat(product.getId()).isEqualTo("1");
	}
    @Test
	void testProductSetDao() {
    	prodset = new ProductSetDto();
    	prodset  = new ProductSetDto("mobile", "in stock", 5);
    	prodset.setProductQuantity(5);
    	prodset.setProductStatus("in stock");
    	prodset.setProductName("mobile");
    	prodset.getProductName();
    	prodset.getProductQuantity();
    	prodset.getProductStatus();
		assertThat(prodset.getProductQuantity()).isEqualTo(5);
	}

}
