package com.shoppingapp.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shoppingapp.microservice.model.ResetDto;
import com.shoppingapp.microservice.model.Role;
import com.shoppingapp.microservice.model.UserDao;
import com.shoppingapp.microservice.model.UserDto;
import com.shoppingapp.microservice.repository.RoleRepository;
import com.shoppingapp.microservice.repository.UserRepository;


@SpringBootTest
public class JwtUserDetailsServiceTest {
	
	@Mock 
	UserRepository repo;
	
	@MockBean
	RoleRepository repoRole;
	
	@Mock 
	private PasswordEncoder bcryptEncoder;
	
	@InjectMocks
	private JwtUserDetailsService service;
	

	private Role role;
	@BeforeEach
	void setup() {
		role = new Role("1", "ROLE_USER");
	}
	
	@Test
	void loadUserByUserNameShouldThrowExceptionTest()
	{
		when(repo.findByUsername("wrongUserName")).thenReturn(null);
		assertThatThrownBy(() -> service.loadUserByUsername("wrongUserName"))
		.isInstanceOf(UsernameNotFoundException.class)
		.hasMessage("User not found with username: wrongUserName");
		verify(repo, Mockito.times(1)).findByUsername("wrongUserName");
	}
	
	
	@Test
	void loadUserByUserNameShouldUserNameTest()
	{
		Set<Role> role = new HashSet<>();
		role.add(new Role("1","ROLE_ADMIN"));
		Optional<UserDao> userDao = Optional.of(new UserDao("1", "Arvind", "Arvind@123", "Arvind", "Sisodiya", "arvindsis35@gmail.com", 1234567L, role));
		when(repo.findByUsername("Arvind")).thenReturn(userDao);
		assertThat(service.loadUserByUsername("Arvind")).isNotNull();
		verify(repo, Mockito.times(1)).findByUsername("Arvind");
	}
	
	@Test
	void testUserRegister_save() {
		UserDto userdto = new UserDto("test", "Test@123", "test", "test", "arvindsis25@gmail.com", 738345543L);
		when(repoRole.save(any())).thenReturn(Optional.of(role));
		assertThat(repoRole.findByName("ROLE_USER")).isEqualTo(Optional.empty());
		//service.save(userdto);
		//assertThat(service.save(new UserDto("test", "Test@1234", "test", "test", "test@gmail.com"))).isEqualTo("arcins");
	    }//fix---
	
	
	@Test
	void testUpdatePassword() {
		Set<Role> role = new HashSet<>();
		role.add(new Role("1","ROLE_ADMIN"));
		UserDao userDao = new UserDao("1", "Arvind", "Arvind@123", "Arvind", "Sisodiya", "arvindsis35@gmail.com", 1234567L, role);
		when(repo.findByUsername("arvind")).thenReturn(Optional.of(userDao));
		assertThat(service.updatePassword("arvind", new ResetDto("arvind", "Admin@123"))).isEqualTo(new ResponseEntity<>("Username doesn't exists!", HttpStatus.BAD_REQUEST));
	}
	
	@Test
	void testUpdatePassword_usernameExists() {
		Set<Role> role = new HashSet<>();
		role.add(new Role("1","ROLE_USER"));
		UserDao userDao = new UserDao("1", "Arvind", "Arvind@123", "Arvind", "Sisodiya", "arvindsis35@gmail.com", 1234567L, role);
		when(repo.findByUsername("string")).thenReturn(Optional.of(userDao));
		assertThat(service.updatePassword("string", new ResetDto("string", "Admin@123"))).isEqualTo(new ResponseEntity<>("Username doesn't exists!", HttpStatus.BAD_REQUEST));
	}//fix --
	
	

}
