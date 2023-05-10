package com.shoppingapp.microservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.microservice.model.Role;
import com.shoppingapp.microservice.repository.RoleRepository;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/shopping/roles")
public class CreateRoleController {

	@Autowired
	RoleRepository repo;
	
	@PostMapping("/create-role")
	@ApiOperation(notes="adding roles into database", value="admin allowed")
	public Role createRole(@RequestBody Role role)
	{
		log.info("role added successfully");
		return repo.save(role);
	}
}
