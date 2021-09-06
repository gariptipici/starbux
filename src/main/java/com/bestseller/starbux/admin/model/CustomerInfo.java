package com.bestseller.starbux.admin.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerInfo {
  @Id
  private Long customerId;

  private BigDecimal orderTotalAmount;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getOrderTotalAmount() {
    return orderTotalAmount;
  }

  public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
    this.orderTotalAmount = orderTotalAmount;
  }
}
