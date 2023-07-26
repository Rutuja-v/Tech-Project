package com.atmmachine.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmmachine.dto.TransactionRequestDTO;

import com.atmmachine.model.User;

import com.atmmachine.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		// Implement validation and save the user to the database
		if (user.getUsername() == null || user.getPassword() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required.");
		}

		// Check if the username already exists
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
		}

		// Save the user to the database
		try {
			userRepository.save(user);
			return ResponseEntity.ok("User registered successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while registering the user.");
		}
	}
	 
}
