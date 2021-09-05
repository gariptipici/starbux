package com.bestseller.starbux.admin.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CustomerInfoDto {
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
