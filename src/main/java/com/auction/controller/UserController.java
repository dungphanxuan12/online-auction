package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/**
	 * Register.
	 *
	 * @param user the user
	 * @return the response entity
	 * @throws Exception the exception
	 */
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

	/**
	 * Verify the user after login
	 *
	 * @param activation token
	 */
	@GetMapping(path = "/verify")
	public ResponseEntity<?> verify(@RequestParam String token) {
		try {
			System.out.println(token);
			userService.active(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
