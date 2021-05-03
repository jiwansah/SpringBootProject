package com.example.exceptionHandling;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcetionHandling {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException exception) {
		if(exception.getMessage() != null || !exception.getMessage().equals("")) {
			return new ResponseEntity<String>(exception.getMessage() , HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Input field is empty please look in the request body", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
		return new ResponseEntity<String>("Request value is not present in DB, please change your request", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<String>("Please change your Http method type", HttpStatus.NOT_FOUND);
	}
}
