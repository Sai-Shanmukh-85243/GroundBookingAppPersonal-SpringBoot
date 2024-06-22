package com.groundbooking.groundbookingmonolythicapp.ExceptionHandling;

import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static java.lang.String.format;

@RestControllerAdvice
public class GlobalExceptionHandling{

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> BadCredentialsHandler(BadCredentialsException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<ExceptionResponse> DataNofFound(DataNotFound ex){
        String message= format("%s %s with %s" ,ex.getEntity(),ex.getMessage(),ex.getValue());
        ExceptionResponse exceptionResponse=new ExceptionResponse(message,false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> RecordAlreadyExits(RecordAlreadyExistsException ex) {
        String message = format("%s %s with the %s", ex.getEntity(), ex.getMessage(), ex.getValue());
        ExceptionResponse exceptionResponse = new ExceptionResponse(message, false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(BookingAlreadyExists.class)
    public ResponseEntity<ExceptionResponse> BookingAlreadyExists(BookingAlreadyExists ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> ActiveBookingPresent(DataIntegrityViolationException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> UserNotFoundHandler(RuntimeException ex){
        String message = format("User not found");
        ExceptionResponse exceptionResponse = new ExceptionResponse(message, false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }


}
