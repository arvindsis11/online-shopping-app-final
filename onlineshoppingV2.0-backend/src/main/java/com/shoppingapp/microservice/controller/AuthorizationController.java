package com.shoppingapp.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.microservice.config.JwtTokenUtil;
import com.shoppingapp.microservice.exception.InvalidInputException;
import com.shoppingapp.microservice.kafka.JsonKafkaProducer;
import com.shoppingapp.microservice.kafka.KafkaProducer;
import com.shoppingapp.microservice.model.JwtRequest;
import com.shoppingapp.microservice.model.JwtResponse;
import com.shoppingapp.microservice.model.ResetDto;
import com.shoppingapp.microservice.model.UserDao;
import com.shoppingapp.microservice.model.UserDto;
import com.shoppingapp.microservice.repository.UserRepository;
import com.shoppingapp.microservice.service.JwtUserDetailsService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api/v1.0/shopping")
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepo;

	private JsonKafkaProducer kafkaProducer;
	
	private KafkaProducer stringProducer;

	@Autowired
	public AuthorizationController(JsonKafkaProducer kafkaProducer,KafkaProducer stringProducer) {

		this.kafkaProducer = kafkaProducer;
		this.stringProducer = stringProducer;
	}

	
	@PostMapping("/login")
	@ApiOperation(notes = "Returns token to authenticate the microservices", value = "authentication")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		log.info("inside login method");
		stringProducer.sendMessage("username:"+authenticationRequest.getUsername());
		stringProducer.sendMessage("password:"+authenticationRequest.getPassword());
		log.info("logged in username and password sent to topic");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), roles));
	}

	@PostMapping("/register")
	@ApiOperation(notes = "saving the details of new user in database", value = "register user")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		log.info("inside registration method");
		kafkaProducer.sendMessage(user);
		log.info("json message sent to topic...");
		return userDetailsService.save(user);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			log.info("authentication in process");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new InvalidInputException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			log.warn("invalid credentials");
			throw new InvalidInputException("INVALID_CREDENTIALS");
		}
	}

	@PostMapping("/authorize")
	@ApiOperation(notes = "checking the authorized user with the help of token", value = "authorized user")
	public boolean authorize(@RequestHeader("Authorization") String token) {
		log.info("token validation here");
		String authToken = null;
		String user = null;
		if (token != null && token.startsWith("Bearer ")) {
			authToken = token.substring(7);
			user = jwtTokenUtil.getUsernameFromToken(authToken);
		}

		if (user == null) {
			log.error("token is null");
			return false;
		}

		return true;
	}

	@PatchMapping("/{username}/forgot") // {username} fix
	@ApiOperation(notes = "checking the authorized user with the help of token", value = "authorized user")
	public ResponseEntity<?> forgotPassword(@PathVariable(name = "username") String username,
			@RequestBody ResetDto reset) {
		log.info("inside forgot password");
		return userDetailsService.updatePassword(username, reset);

	}
	//--temp--fix
	@GetMapping("/getProfile/{username}")
	@ApiOperation(notes = "profile of user", value = "for testing purpose only")
	public UserDao getProfile(@PathVariable(name = "username") String username) {
		return userRepo.findByUsername(username).get();
	}
}
