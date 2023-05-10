/**
 * 
 */
package com.shoppingapp.microservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingapp.microservice.model.Role;

/**
 * @author 2101931
 *
 */
@DataJpaTest
class RoleRepositoryTest {

	@MockBean
	RoleRepository repo;
	
	private Role role;
	@BeforeEach
	void setup() {
		role = new Role("1", "ROLE_ADMIN");
	}
	/**
	 * Test method for {@link com.shoppingapp.microservice.repository.RoleRepository#findByName(java.lang.String)}.
	 */
	@Test
	void testFindByName() {
		when(repo.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
		assertThat(repo.findByName("ROLE_ADMIN")).isEqualTo(Optional.of(role));
	}

}
