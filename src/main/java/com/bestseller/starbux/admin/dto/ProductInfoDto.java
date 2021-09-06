package com.bestseller.starbux.admin.dto;


public class ProductInfoDto {
  private Long productId;
  private Long sideProductId;

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getSideProductId() {
    return sideProductId;
  }

  public void setSideProductId(Long sideProductId) {
    this.sideProductId = sideProductId;
  }
}
