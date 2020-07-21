package com.higor.make_magic.resource.exception;

import com.higor.make_magic.domain.dto.StandardError;
import com.higor.make_magic.domain.service.exception.InvalidHouseException;
import com.higor.make_magic.domain.service.exception.ResourceNotFoundException;
import com.higor.make_magic.domain.service.exception.ThirdPartyApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    public StandardError standardErrorFactory(Instant date, String path){
        return new StandardError(path, date);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<StandardError> badRequestHandler(ConstraintViolationException ex, HttpServletRequest request){
        StandardError error = this.standardErrorFactory(Instant.now(), request.getRequestURI());

        ex.getConstraintViolations().forEach(
                it -> error.setMessages(it.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        StandardError error = this.standardErrorFactory(Instant.now(), request.getRequestURI());
        error.setMessages(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(ThirdPartyApiException.class)
    public ResponseEntity<StandardError> potterApi(ThirdPartyApiException ex, HttpServletRequest request){
        StandardError error = this.standardErrorFactory(Instant.now(), request.getRequestURI());
        error.setMessages(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(error);
    }

    @ExceptionHandler(InvalidHouseException.class)
    public ResponseEntity<StandardError> invalidHouseException(InvalidHouseException ex, HttpServletRequest request){
        StandardError error = this.standardErrorFactory(Instant.now(), request.getRequestURI());
        error.setMessages(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}