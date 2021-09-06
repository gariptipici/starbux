package com.bestseller.starbux.shop.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
class OrderRepositoryTest {

  private static final Long TEST_DATA_CUSTOMER_ID = 3L;
  private static final Long TEST_DATA_ORDER_ID = 2L;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void findAllByCustomerIdTest() {
    Assertions.assertFalse(orderRepository.findAllByCustomerId(TEST_DATA_CUSTOMER_ID).isEmpty());
  }

  @Test
  void findAllByCustomerIdTestNotFound() {
    Assertions.assertTrue(orderRepository.findAllByCustomerId(1L).isEmpty());
  }


  @Test
  void findById_AndCustomerIdTest() {
    Assertions
        .assertNotNull(
            orderRepository.findById_AndCustomerId(TEST_DATA_ORDER_ID, TEST_DATA_CUSTOMER_ID));
  }

  @Test
  void findById_AndCustomerIdTestNotFound() {
    Assertions
        .assertFalse(orderRepository.findById_AndCustomerId(1L, TEST_DATA_CUSTOMER_ID).isPresent());
  }

}
