package com.fis.microservices.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "subscription_id")
  private Integer subscriptionId;
  
  @Column(name = "subscriber_name")
  private String subscriberName;
  
  @Column(name = "date_subscribed")
  private Date dateSubscribed;
  
  @Column(name = "date_returned")
  private Date dateReturned;
  
  @Column(name = "book_id")
  private Integer bookId;

  public Integer getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(Integer subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getSubscriberName() {
    return subscriberName;
  }

  public void setSubscriberName(String subscriberName) {
    this.subscriberName = subscriberName;
  }

  public Date getDateSubscribed() {
    return dateSubscribed;
  }

  public void setDateSubscribed(Date dateSubscribed) {
    this.dateSubscribed = dateSubscribed;
  }

  public Date getDateReturned() {
    return dateReturned;
  }

  public void setDateReturned(Date dateReturned) {
    this.dateReturned = dateReturned;
  }

  public Integer getBookId() {
    return bookId;
  }

  public void setBookId(Integer bookId) {
    this.bookId = bookId;
  }
  
  
  
}
