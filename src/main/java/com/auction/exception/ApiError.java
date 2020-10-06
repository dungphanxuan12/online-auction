package com.auction.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {

	private Date timestamp;

	private String message;

	private List<String> errors;
}
