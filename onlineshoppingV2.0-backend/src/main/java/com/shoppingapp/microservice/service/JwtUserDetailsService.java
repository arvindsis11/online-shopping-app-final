package com.shoppingapp.microservice.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoppingapp.microservice.model.ResetDto;
import com.shoppingapp.microservice.model.Role;
import com.shoppingapp.microservice.model.UserDao;
import com.shoppingapp.microservice.model.UserDto;
import com.shoppingapp.microservice.repository.RoleRepository;
import com.shoppingapp.microservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDao> user = userRepo.findByUsername(username);
		if (user == null) {
			log.error("user not found with username");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public ResponseEntity<?> save(UserDto user) {
		UserDao newUser = new UserDao();
		log.info(user.toString());
		if (userRepo.existsByUsername(user.getUsername())) {
			log.info("username is already exists");
			return new ResponseEntity<>("Username is already exists!", HttpStatus.BAD_REQUEST);
		}

		// add check for email exists in DB
		if (userRepo.existsByEmailid(user.getEmailid())) {
			log.info("Email is already exists!");
			return new ResponseEntity<>("Email is already exists!", HttpStatus.BAD_REQUEST);
		}
		if (!isValidEmailAddress(user.getEmailid())) {
			log.info("invalid email id!");
			return new ResponseEntity<>("please enter valid email!", HttpStatus.BAD_REQUEST);
		}
		Optional<UserDao> usernm = userRepo.findByUsername(user.getUsername());
		if (usernm.isPresent()) {// for username uniqueness check!
			log.info("username is already exists!");
			return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
		}

		if (!isValidPassword(user.getPassword())) {
			log.warn("password should match constraints");
			return new ResponseEntity<>(
					"password must contain 8 char at least one lowercase, one upper case, one number and the special charecters",
					HttpStatus.BAD_REQUEST);
		}
		try {
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setFirstname(user.getFirstname());
			newUser.setLastname(user.getLastname());
			newUser.setEmailid(user.getEmailid());
			newUser.setContactno(user.getContactno());
			Role roles = roleRepository.findByName("ROLE_USER").get();
			newUser.setRoles(Collections.singleton(roles));
			userRepo.save(newUser);
			log.info("registeration successful...");
			return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Please add roles into db first", HttpStatus.OK);
		}

	}

	public ResponseEntity<?> updatePassword(String username, ResetDto reset) {
		log.info("inside updatepassword method");
		if (!userRepo.existsByUsername(username)) {
			return new ResponseEntity<>("Username doesn't exists!", HttpStatus.BAD_REQUEST);
		}
		if (isValidPassword(reset.getPassword())) {
			Optional<UserDao> usernm = userRepo.findByUsername(username);
			usernm.get().setPassword(bcryptEncoder.encode(reset.getPassword()));
			userRepo.save(usernm.get());
			log.info("password updated");
			return new ResponseEntity<>("changed password successfully!", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(
				"password must contain 8 char at least one lowercase, one upper case, one number and the special charecters",
				HttpStatus.BAD_REQUEST);

	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public boolean isValidPassword(String password) {
		String ePattern = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(password);
		return m.matches();
	}
}