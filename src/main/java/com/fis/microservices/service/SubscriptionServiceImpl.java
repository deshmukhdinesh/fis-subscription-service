package com.fis.microservices.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.fis.microservices.dto.SubscriptionRequest;
import com.fis.microservices.dto.SubscriptionStatus;
import com.fis.microservices.entity.Subscription;
import com.fis.microservices.exception.SubscriptionException;
import com.fis.microservices.proxy.BookServiceProxy;
import com.fis.microservices.repository.SubscriberRespository;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {
  
  
  @Autowired
  private SubscriberRespository respository;  
  
  @Autowired
  private BookServiceProxy bookServiceProxy;
  
  @Autowired
  private RestTemplate restTemplate;
  
  private final String SUBSCRIBE_FAIL ="Books are not available for subscription please visit tomorrow !!!";
  private final String UNSUBSCRIBE_FAIL ="We have all copies of book with us please check book you are Unsubscribeing !!!";

  @Override
  public List<Subscription> getSubscriptionList() {
    
    return respository.findAll();
  }

  @Override
  public List<Subscription> getSubscriptionListBySubscriberName(String subscriberName) {
    // TODO Auto-generated method stub
    return respository.findBySubscriberName(subscriberName);
  }

  
  @Override
  public Subscription subscribeBook(Subscription subscription) {
    SubscriptionRequest request = new SubscriptionRequest(subscription.getBookId(),"SUBSCRIBE");
    
    return subscribeBookWithFeign(subscription);
    /*
     * Subscription subscribe = null; SubscriptionStatus SubscriptionStatus =
     * restTemplate.patchForObject("http://localhost:8082/books/addBook",request,SubscriptionStatus.
     * class); if(SubscriptionStatus.getTransactionStatus().equals("Success")) { subscribe=
     * respository.save(subscription); } else { throw new SubscriptionException(SUBSCRIBE_FAIL); }
     * 
     * return subscribe;
     */
    
  }  

 
  @Override
  public Subscription unSubscribeBook(Subscription subscription) {
    SubscriptionStatus SubscriptionStatus = null;
    Subscription subscriptionToUpdate = respository.findById(subscription.getSubscriptionId()).orElse(null);
    if(subscriptionToUpdate != null) {
      SubscriptionRequest request = new SubscriptionRequest(subscription.getBookId(),"UNSUBSCRIBE");   
      SubscriptionStatus = restTemplate.postForObject("http://localhost:8082/books/updateLibrary",request,SubscriptionStatus.class);
     
    }
     if(SubscriptionStatus.getTransactionStatus().equals("Success")) {
       java.util.Date today = new java.util.Date();
       subscriptionToUpdate.setDateReturned(new Date(today.getYear(),today.getMonth(),today.getDate()));
       subscriptionToUpdate= respository.save(subscriptionToUpdate);
    }
    else {
      throw new SubscriptionException(UNSUBSCRIBE_FAIL);
    }
    
    return subscriptionToUpdate;
    
  }
  
  

  public Subscription subscribeBookWithFeign(Subscription subscription) {
    SubscriptionRequest request = new SubscriptionRequest(subscription.getBookId(),"SUBSCRIBE");
    Subscription subscribe = null;
   // SubscriptionStatus SubscriptionStatus = restTemplate.patchForObject("http://localhost:8082/books/addBook",request,SubscriptionStatus.class);
    
    
    ResponseEntity<SubscriptionStatus> SubscriptionStatus = bookServiceProxy.updateLibrary(request);
    if(SubscriptionStatus.getBody().getTransactionStatus().equals("Success")) {
      subscribe= respository.save(subscription);
    }
    else {
      throw new SubscriptionException(SUBSCRIBE_FAIL);
    }
    
    return subscribe;
    
  }  

}
