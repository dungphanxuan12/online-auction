package com.auction.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {
	@JsonProperty("status")
	private HttpStatus status;
	
	@JsonProperty("timestamp")
	private LocalDateTime timestamp;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("debugMessage")
	private String debugMessage;
	
	@JsonProperty("throwable")
	private Throwable throwable;
	
	@JsonProperty("errors")
	private List<String> errors;
}
