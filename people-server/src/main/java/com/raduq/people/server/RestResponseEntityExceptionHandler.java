package com.raduq.people.server;

import com.raduq.people.server.person.PersonNotFoundException;
import com.raduq.people.server.person.address.AddressNotFoundException;
import com.raduq.people.server.person.pet.PetNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
	extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {PersonNotFoundException.class, PetNotFoundException.class, AddressNotFoundException.class})
	protected ResponseEntity<?> handleNotFound(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
