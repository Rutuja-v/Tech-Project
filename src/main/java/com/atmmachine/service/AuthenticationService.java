//package com.atmmachine.service;
//
//import com.atmmachine.config.JwtTokenUtil;
//import com.atmmachine.dto.AuthenticationRequestDTO;
//import com.atmmachine.dto.AuthenticationResponseDTO;
//import com.atmmachine.model.User;
//import com.atmmachine.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//	private final AuthenticationManager authenticationManager;
//
//	private final UserDetailsService userDetailsService;
//	private final JwtTokenUtil jwtTokenUtil;
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	@Autowired
//
//	public AuthenticationService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
//			JwtTokenUtil jwtTokenUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		this.authenticationManager = authenticationManager;
//		this.userDetailsService = userDetailsService;
//		this.jwtTokenUtil = jwtTokenUtil;
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	public ResponseEntity<?> login(AuthenticationRequestDTO authenticationRequest) {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//		} catch (BadCredentialsException e) {
//			System.out.println("Invalid ");
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//		}
//		try {
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//			final String token = jwtTokenUtil.generateToken(userDetails);
//
//			User user = userRepository.findByUsername(authenticationRequest.getUsername());
//			return ResponseEntity.ok(new AuthenticationResponseDTO(user.getId(), user.getUsername(), token));
//		} catch (UsernameNotFoundException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
//		}
//	}
//}

