package com.shoppingapp.microservice.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description ="Model for the jwt Response for authorization")
public class JwtResponse implements Serializable {

	@ApiModelProperty(notes ="Serial version Id")
	private static final long serialVersionUID = -8091879091924046844L;
	
	@ApiModelProperty(notes="JWT token")
	private final String jwttoken;
	private String username;
	private List<String> roles;
	public JwtResponse(String jwttoken,String username,List<String> roles) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.roles = roles;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public List<String> getRoles() {
		return roles;
	}
}