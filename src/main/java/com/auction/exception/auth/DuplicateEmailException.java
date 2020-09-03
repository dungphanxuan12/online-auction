package com.auction.exception.auth;

public class DuplicateEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2751872468087964913L;

	public DuplicateEmailException(String email) {
		super("User with email: " + email + " already exists");
	}

	public DuplicateEmailException(String email, Throwable throwable) {
		super("User with email: " + email + " already exists", throwable);
	}

}
