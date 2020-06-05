package com.example.myappDB.exception;

public class ApiRequestException extends RuntimeException {
	
	public ApiRequestException(String message) {
		super(message);
	}
	
	// throwable
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}