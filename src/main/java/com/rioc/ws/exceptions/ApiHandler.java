package com.rioc.ws.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandler {

	@ExceptionHandler( value = (ApiException.class))
	public ResponseEntity<Object> handleException(ApiException apiExecption){
		ExceptionMessage exceptionMessage = new ExceptionMessage(apiExecption.getMessage());
		return new ResponseEntity<>(exceptionMessage, apiExecption.getHttpStatus());
	}
	
	
}
