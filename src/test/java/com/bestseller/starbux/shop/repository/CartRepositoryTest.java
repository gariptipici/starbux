package com.bestseller.starbux.shop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
public class CartRepositoryTest {

  private static final Long TEST_DATA_CUSTOMER_ID = 3L;
  private static final Long TEST_DATA_CART_ID = 5L;

  @Autowired
  CartRepository cartRepository;

  @Test
  public void findById_AndCustomerIdTest() {
    Assert
        .isTrue(cartRepository.findById_AndCustomerId(TEST_DATA_CART_ID, TEST_DATA_CUSTOMER_ID).isPresent(),
            "Object is null");
  }

  @Test
  public void findById_AndCustomerIdTesjNotFound() {
    Assert
        .isTrue(!cartRepository.findById_AndCustomerId(TEST_DATA_CART_ID, 1L).isPresent(),
            "Object is null");
  }

}
