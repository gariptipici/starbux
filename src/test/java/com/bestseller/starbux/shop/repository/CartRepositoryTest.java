package com.bestseller.starbux.shop.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
class CartRepositoryTest {

  private static final Long TEST_DATA_CUSTOMER_ID = 3L;
  private static final Long TEST_DATA_CART_ID = 5L;

  @Autowired
  CartRepository cartRepository;

  @Test
  void findById_AndCustomerIdTest() {
    Assertions
        .assertTrue(cartRepository.findById_AndCustomerId(TEST_DATA_CART_ID, TEST_DATA_CUSTOMER_ID)
            .isPresent());
  }

  @Test
  void findById_AndCustomerIdTesjNotFound() {
    Assertions
        .assertFalse(cartRepository.findById_AndCustomerId(TEST_DATA_CART_ID, 1L).isPresent());
  }

}
