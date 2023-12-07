package com.luchonicolosi.willitaapi.exceptions;

import com.luchonicolosi.willitaapi.dto.ErrorDto;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFound ex, WebRequest req) {
        logger.error(ex.getMessage());
        ErrorDto response = new ErrorDto("Recurso [" + ex.getResource() + "] con ID: " + ex.getResourceId(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
