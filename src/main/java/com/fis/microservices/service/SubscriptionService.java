package com.fis.microservices.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fis.microservices.entity.Subscription;

@Service
public interface SubscriptionService {
  
  Subscription subscribeBook(Subscription subscription);
  
  Subscription unSubscribeBook(Subscription subscription);
  
  List<Subscription> getSubscriptionList();
  
  List<Subscription> getSubscriptionListBySubscriberName(String subscriberName);
  
  

}
