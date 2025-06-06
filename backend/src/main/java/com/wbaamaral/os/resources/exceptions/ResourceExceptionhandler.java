package com.wbaamaral.os.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wbaamaral.os.services.exceptions.DataIntegratyViolationException;
import com.wbaamaral.os.services.exceptions.NoResourceFoundException;
import com.wbaamaral.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResourceExceptionhandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(ObjectNotFoundException e) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(NoResourceFoundException e) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_IMPLEMENTED.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(error);
	}

	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(DataIntegratyViolationException e) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(MethodArgumentNotValidException e) {
	
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos!");
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(ConstraintViolationException e) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundExcetion(org.springframework.web.servlet.resource.NoResourceFoundException e) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_IMPLEMENTED.value(),
				"Rota não implementada!");

		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(error);
	}
	
}
