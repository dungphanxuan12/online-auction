package com.auction.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auction.exception.auth.DuplicateEmailException;

/**
 * 
 * @author github.com/dungphanxuan12
 * 
 * @since 2/9/2020
 *
 */
@RestControllerAdvice
public class ApiHandlerException {

	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = { DuplicateEmailException.class })
	public ResponseEntity<Object> handleDuplicateEmail(DuplicateEmailException ex) {
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(), null,
				ex, errors);

		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}
}
