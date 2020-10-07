package com.auction.utils;

public interface IEmailService {

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @return
	 */
	Boolean sendMail(String to, String subject, String text);
}
