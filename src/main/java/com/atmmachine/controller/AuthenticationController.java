//package com.atmmachine.controller;
//
//import com.atmmachine.dto.AuthenticationRequestDTO;
//import com.atmmachine.dto.AuthenticationResponseDTO;
//import com.atmmachine.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//	private final AuthenticationService authenticationService;
//
//	@Autowired
//	public AuthenticationController(AuthenticationService authenticationService) {
//		this.authenticationService = authenticationService;
//	}
//
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequest) {
//		return authenticationService.login(authenticationRequest);
//	}
//}
