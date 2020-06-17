package com.example.demoexception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class AppExceptionHandler {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        //details.add(ex.getLocalizedMessage());
        details.add(ex.getMessage());
        UserNotFoundException exception = new UserNotFoundException("EXCEPTION OCCURED");
        return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        UserNotFoundException exception = new UserNotFoundException("EXCEPTION OCCURED");
        return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
    }
}
