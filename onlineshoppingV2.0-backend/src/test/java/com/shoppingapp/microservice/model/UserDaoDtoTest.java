package com.shoppingapp.microservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoDtoTest {
	
	private UserDao user1;
	private UserDto user2;
	private Role role;
	private ResetDto reset;
	
	@Test
	void testUserDao() throws Exception
	{
		Set<Role> role = new HashSet<>();
		role.add(new Role("1", "ROLE_ADMIN"));
		user1 = new UserDao();
		user1 = new UserDao("1", "user", "pass","Arvind", "Sisodiya", "arvindsis35@gmail.com", 123456L,role);
		user1.setId("1");
		user1.setFirstname("Arvind");
		user1.setLastname("sisodiya");
		user1.setEmailid("arvindsis35@gmail.com");
		user1.setUsername("test");
		user1.setPassword("test");
		user1.getId();
		user1.getFirstname();
		user1.getLastname();
		user1.getEmailid();
		user1.getUsername();
		user1.getPassword();
		
	}
	
	@Test
	void testUserDto() throws Exception
	{
		user2 = new UserDto();
		user2 = new UserDto("user", "pass","Arvind", "Sisodiya", "arvindsis35@gmail.com", 123456L);
		user2.setFirstname("Arvind");
		user2.setLastname("sisodiya");
		user2.setEmailid("arvindsis35@gmail.com");
		user2.setUsername("test");
		user2.setPassword("test");
		user2.getFirstname();
		user2.getLastname();
		user2.getEmailid();
		user2.getUsername();
		user2.getPassword();
		
	}
	
	@Test
	void testRole(){
		role = new Role();
		role = new Role("1", "ROLE_ADMIN");
		role.setId("1");
		role.getId();
		role.setName("ROLE_USER");
		role.getName();
		assertThat(role).isEqualTo(new Role("1", "ROLE_USER"));
	}
	
	@Test
	void testResetDto() {
		reset = new ResetDto();
		reset = new ResetDto("arvin", "Arvind@123");
		reset.setUsername("test");
		reset.setPassword("Test@123");
		reset.getUsername();
		reset.getPassword();
		assertThat(reset.getUsername()).isEqualTo("test");
	}

}
