package com.energy.accomdom.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.energy.accomdom.model.response.ErrorMessageResponse;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}

		ErrorMessageResponse errorMessage = new ErrorMessageResponse(400, errorMessageDescription);

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NullPointerException.class, ServiceException.class })
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}

		ErrorMessageResponse errorMessage = new ErrorMessageResponse(500, errorMessageDescription);

		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}

		ErrorMessageResponse errorMessage = new ErrorMessageResponse(404, errorMessageDescription);

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<Object> handleAuthenticationException(BadCredentialsException ex) {
		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		} else {errorMessageDescription = "Invalid username or password";}

		ErrorMessageResponse errorMessage = new ErrorMessageResponse(401, errorMessageDescription);

		return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	}
}
