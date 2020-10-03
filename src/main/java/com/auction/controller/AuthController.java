package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.config.UserDetailServiceCustom;
import com.auction.dto.UserDTO;
import com.auction.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceCustom userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(path = "/login")
	ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		UserDetails userDetails = userDetailService.loadUserByUsername(userDTO.getEmail());
		String jwt = jwtUtil.generateToken(userDetails);
		userDTO.setJwtToken(jwt);

		return ResponseEntity.ok(userDTO);
	}

}
