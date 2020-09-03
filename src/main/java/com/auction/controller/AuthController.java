package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.entity.UserEntity;
import com.auction.exception.auth.DuplicateEmailException;
import com.auction.service.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IUserService userService;

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
		try {
			UserEntity userExists = userService.findByEmail(user.getEmail());
			if (userExists != null) {
				throw new DuplicateEmailException(user.getEmail());
			}

			userService.save(user);
			return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		}
	}
}
