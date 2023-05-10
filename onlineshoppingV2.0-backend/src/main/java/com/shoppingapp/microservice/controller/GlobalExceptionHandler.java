package com.shoppingapp.microservice.controller;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shoppingapp.microservice.exception.InvalidInputException;
import com.shoppingapp.microservice.model.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler({ InvalidInputException.class })
	public ErrorResponseDTO InvalidWalletBalanceException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

}
