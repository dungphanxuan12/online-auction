package com.auction.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8487855518763560257L;

	public UserNotFoundException(String email) {
		super("User NOT FOUND with that email address: " + email);
	}

}
