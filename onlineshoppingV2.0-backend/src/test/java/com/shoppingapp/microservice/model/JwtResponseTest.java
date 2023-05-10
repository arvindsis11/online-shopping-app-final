package com.shoppingapp.microservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtResponseTest {
	
	private JwtResponse jwtResArg;
	@BeforeEach
	void setUp() throws Exception
	{
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		jwtResArg = new JwtResponse("token", "arvind", roles);
	}
	
	@Test
	void test()
	{
		assertThat(jwtResArg.getToken().equals("token"));
	}

}
