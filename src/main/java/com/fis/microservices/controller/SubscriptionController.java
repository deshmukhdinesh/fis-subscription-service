package com.fis.microservices.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fis.microservices.entity.Subscription;
import com.fis.microservices.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
  
  @Autowired
  private SubscriptionService subscriptionService;
  
  @GetMapping("")
  public  HttpEntity<List<Subscription>> getAllSubscription() {
    List<Subscription> listOfSubscription = null;
    HttpStatus requestedBookStatus = HttpStatus.OK;
    try {
      listOfSubscription =subscriptionService.getSubscriptionList();
      if(listOfSubscription==null) {
        requestedBookStatus=HttpStatus.BAD_REQUEST;
      }
    } catch (Exception e) {
      requestedBookStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }  

    return new ResponseEntity<>(listOfSubscription, requestedBookStatus);
   
  }
  
  @GetMapping("/{subscriber_name}")
  public  HttpEntity<List<Subscription>> getSubscription(@PathVariable("subscriber_name") String subscriberName) {

    List<Subscription> listOfSubscription = null;
    HttpStatus requestedBookStatus = HttpStatus.ACCEPTED;
    try {
      listOfSubscription =subscriptionService.getSubscriptionListBySubscriberName(subscriberName);
      if(listOfSubscription==null) {
        requestedBookStatus=HttpStatus.BAD_REQUEST;
      }
    } catch (Exception e) {
      requestedBookStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }   

    return new ResponseEntity<>(listOfSubscription, requestedBookStatus);
   
  }
  
  
  @PostMapping("/subscribe")
  public ResponseEntity subscribeBook(@RequestBody Subscription subscription) throws Exception{
    
    Subscription subscribeBook = null;
    HttpStatus subscribeStatus = HttpStatus.CREATED;
    subscribeBook =subscriptionService.subscribeBook(subscription);
    
    /* try {
      subscribeBook =subscriptionService.subscribeBook(subscription) ;
    } catch (SubscriptionException e) {
    //  e.printStackTrace();
      subscribeStatus = HttpStatus.BAD_REQUEST;
    }
    catch (Exception e) {
      //  e.printStackTrace();
        subscribeStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      }*/

    return new ResponseEntity(subscribeBook, subscribeStatus);
    
  }
  
  @PostMapping("/unsubscribe")
  public ResponseEntity UnsubscribeBook(@RequestBody Subscription subscription) throws Exception{
    
    Subscription subscribeBook = null;
    HttpStatus subscribeStatus = HttpStatus.CREATED;
    subscribeBook =subscriptionService.unSubscribeBook(subscription) ; 
    return new ResponseEntity(subscribeBook, subscribeStatus);
    
  }

}
