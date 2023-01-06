package com.fis.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler({ SubscriptionException.class })
  public ResponseEntity exception(SubscriptionException exception) {
     return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity globalException(Exception exception) {
     return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
