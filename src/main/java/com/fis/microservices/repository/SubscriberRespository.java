package com.fis.microservices.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fis.microservices.entity.Subscription;

@Repository
public interface SubscriberRespository extends JpaRepository<Subscription, Integer> {
  
  
  List<Subscription> findBySubscriberName(String subscriberName);
  
 
  
  

}
