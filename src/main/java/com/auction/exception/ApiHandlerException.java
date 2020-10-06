package com.auction.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author github.com/dungphanxuan12
 * 
 * @since 2/9/2020
 *
 */
@RestControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {

	/**
	 * Handle any exception
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String messages = ex.getLocalizedMessage();
		if (messages == null)
			messages = ex.toString();
		ApiError apiError = new ApiError(new Date(), messages, null);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle NullpointerException
	 */
	@ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
		String messages = ex.getLocalizedMessage();
		if (messages == null)
			messages = ex.toString();
		ApiError apiError = new ApiError(new Date(), messages, null);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
