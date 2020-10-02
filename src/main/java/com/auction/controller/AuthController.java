package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.auction.converter.UserConverter;
import com.auction.dto.UserDTO;
import com.auction.entity.UserEntity;
import com.auction.service.IUserService;
import com.auction.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IUserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceCustom userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserEntity> register(@RequestBody UserDTO user) {
		UserEntity oldUser = userService.findByEmail(user.getEmail());
		if (oldUser != null) {
			System.out.println("existing email " + " " + user.getEmail());
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		}
		userService.save(UserConverter.convertToEntity(user));

		return new ResponseEntity<>(UserConverter.convertToEntity(user), HttpStatus.OK);
	}

	@PostMapping(path = "/login")
	ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
		try {
			authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		UserDetails userDetails = userDetailService.loadUserByUsername(userDTO.getEmail());
		String jwt = jwtUtil.generateToken(userDetails);
		userDTO.setJwtToken(jwt);

		return ResponseEntity.ok(userDTO);
	}

}
