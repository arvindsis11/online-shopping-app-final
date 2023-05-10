package com.shoppingapp.microservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingapp.microservice.model.Role;
import com.shoppingapp.microservice.model.UserDao;
import com.shoppingapp.microservice.model.UserDto;

@DataJpaTest
public class UserRepositoryTest {
	
	@MockBean
	private UserRepository repo;
	
	private Set<Role> role;
	private UserDao user;
	
	@BeforeEach
	void setup() {
		role  = new HashSet<>();
		role.add(new Role("1","ROLE_ADMIN"));
		user = new UserDao("1", "admin" ,"password", "Arvind", "Sisodiya", "arvindsis35@gmail.com",1234567L, role);
	}
	
	@AfterEach
	void tearDown() {
		repo.deleteAll();
	}
	
	@Test
	void testUserRepositoryFindByname()
	{
		when(repo.findByUsername("admin")).thenReturn(Optional.of(user));
		assertThat(repo.findByUsername("admin")).isEqualTo(Optional.of(user));
				
	}
	
	@Test
	void testUserDaoSave() throws Exception {
	UserDto user = new UserDto("admin", "pass", "arvind", "sisodiya", "arvindsis35@gmail.com", 1234567L);
	UserDao newUser = new UserDao();
	newUser.setUsername(user.getUsername());
	newUser.setPassword(user.getPassword());
	newUser.setFirstname(user.getFirstname());
	newUser.setLastname(user.getLastname());
	newUser.setEmailid(user.getEmailid());
	when(repo.save(newUser)).thenReturn(newUser);
	assertThat(repo.save(newUser)).isEqualTo(newUser);
	}
	
	@Test
	void testFindByEmailid() {
		when(repo.findByEmailid("arvindsis35@gmail.com")).thenReturn(Optional.of(user));
		//assertThat(repo.findByUsername("arvindsis35@gmail.com")).isEqualTo(Optional.of(user));
	}
	
	@Test
	void testFindByUsernameOrEmailid() {
		when(repo.findByUsernameOrEmailid("admin", "arvindsis35@gmail.com")).thenReturn(Optional.of(user));
		assertThat(repo.findByUsernameOrEmailid("admin", "arvindsis35@gmail.com")).isEqualTo(Optional.of(user));
	}
	
	@Test
	void testExistsByUsername() {
		when(repo.existsByUsername("admin")).thenReturn(true);
		assertThat(repo.existsByUsername("admin")).isEqualTo(true);
	}
	
	@Test
	void testExistsByEmailid() {
		when(repo.existsByEmailid("arvindsis35@gmail.com")).thenReturn(true);
		assertThat(repo.existsByEmailid("arvindsis35@gmail.com")).isEqualTo(true);
	}
	

}
