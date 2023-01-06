package com.fis.microservices.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.fis.microservices.dto.SubscriptionRequest;
import com.fis.microservices.dto.SubscriptionStatus;


@FeignClient(name="book-service", url = "localhost:8082")
public interface BookServiceProxy {
  
  @PostMapping("/books/updateLibrary")
  public ResponseEntity<SubscriptionStatus> updateLibrary(SubscriptionRequest subscriptionRequest );

}
