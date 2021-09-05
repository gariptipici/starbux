package com.bestseller.starbux.shop.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
public class OrderRepositoryTest {

  private static final Long TEST_DATA_CUSTOMER_ID = 3L;
  private static final Long TEST_DATA_ORDER_ID = 2L;

  @Autowired
  OrderRepository orderRepository;

  @Test
  public void findAllByCustomerIdTest() {
    Assert.notEmpty(orderRepository.findAllByCustomerId(TEST_DATA_CUSTOMER_ID), "List is empty");
  }

  @Test
  public void findAllByCustomerIdTestNotFound() {
    Assert.isTrue(orderRepository.findAllByCustomerId(1L).isEmpty(), "List not empty");
  }


  @Test
  public void findById_AndCustomerIdTest() {
    Assert
        .notNull(orderRepository.findById_AndCustomerId(TEST_DATA_ORDER_ID, TEST_DATA_CUSTOMER_ID),
            "Object is null");
  }

  @Test
  public void findById_AndCustomerIdTestNotFound() {
    Assert
        .isTrue(!orderRepository.findById_AndCustomerId(1L, TEST_DATA_CUSTOMER_ID).isPresent(),
            "Object is null");
  }

}
