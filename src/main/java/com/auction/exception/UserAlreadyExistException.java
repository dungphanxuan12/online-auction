package com.auction.exception;

public class UserAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -418808941117582949L;

	public UserAlreadyExistException(String email) {
		super("There is an account with that email address: " + email);
	}
}
