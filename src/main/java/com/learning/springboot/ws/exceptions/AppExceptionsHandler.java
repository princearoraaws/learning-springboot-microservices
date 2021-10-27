package com.learning.springboot.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.springboot.ws.ui.model.response.ErrorObject;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity handleAnyException(Exception ex, WebRequest request) {
		System.out.println("handleAnyException called");
		
		ErrorObject error = new ErrorObject(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class, BusinessException.class})
	public ResponseEntity handleSpecificException(Exception ex, WebRequest request) {
		System.out.println("handleNullPointerException called" +ex);
		
		ErrorObject error = new ErrorObject(new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity(ex,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * @ExceptionHandler(value = {BusinessException.class}) public ResponseEntity
	 * handleBusinessException(BusinessException ex, WebRequest request) {
	 * System.out.println("handleBusinessException called");
	 * 
	 * ErrorObject error = new ErrorObject(new Date(), ex.getLocalizedMessage());
	 * 
	 * return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
