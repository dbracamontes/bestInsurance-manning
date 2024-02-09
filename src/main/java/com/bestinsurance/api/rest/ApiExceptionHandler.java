package com.bestinsurance.api.rest;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.bestinsurance.api.dto.ErrorMessage;
import com.bestinsurance.api.exceptions.NotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage notFoundHandler(NotFoundException ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setTimeStamp(LocalDateTime.now());
		errorMessage.setErrorMessage(ex.getLocalizedMessage());

		return errorMessage;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage validationHandler(MethodArgumentNotValidException ex, WebRequest request) {

		List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage())).toList();

		return new ErrorMessage(LocalDateTime.now(), "Request Body with missing info please check error list", errors);
	}
}
