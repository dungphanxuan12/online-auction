package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auction.dto.UserDTO;
import com.auction.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/home")
	public String home() {
		return "Hello";
	}

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody UserDTO user) throws Exception {
		try {
			userService.register(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/verify")
	public ResponseEntity<?> verify(@RequestParam String token) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.getCredentials();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
