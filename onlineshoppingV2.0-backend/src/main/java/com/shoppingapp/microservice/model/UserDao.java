package com.shoppingapp.microservice.model;

import java.util.Set;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model to be used for storing user details")
@Document(collection = "users")
public class UserDao {
	@Id
	@ApiModelProperty(notes = "for id")
	private String id;
	
	@ApiModelProperty(notes = "for name")
	private String username;
	
	@JsonIgnore
	@ApiModelProperty(notes = "for password")
	private String password;
	@ApiModelProperty(notes = "for firstname")
	private String firstname;
	@ApiModelProperty(notes = "for lastname")
	private String lastname;
	@ApiModelProperty(notes = "for emailid")
	private String emailid;	
	@ApiModelProperty(notes = "for contact no")
	private Long contactno;
	@DBRef
	private Set<Role> roles;

}