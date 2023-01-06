package com.fis.microservices.exception;

import org.springframework.stereotype.Component;


public class SubscriptionException extends RuntimeException{

  private static final long serialVersionUID = 1L;
  
  private String errorMessage;
  
  public SubscriptionException(String message) {
    super(message);
    this.errorMessage= message;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
  
}
