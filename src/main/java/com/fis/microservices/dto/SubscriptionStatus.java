package com.fis.microservices.dto;

import org.springframework.stereotype.Component;


public class SubscriptionStatus {
  private String transactionStatus;
  private String message;
  

  public String getTransactionStatus() {
    return transactionStatus;
  }


  public String getMessage() {
    return message;
  }
  

}
