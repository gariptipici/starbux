package com.bestseller.starbux.admin.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;


@DataJpaTest
public class CustomerInfoReportRepositoryTest {

  private static final Long TEST_DATA_CUSTOMER_ID = 3L;
  private static final Double TEST_DATA_TOTAL_AMOUNT = 40.50;
  @Autowired
  CustomerInfoReportRepository customerInfoReportRepository;

  @Test
  public void getTotalAmountPerCustomerTest(){
    List<Object[]> result = customerInfoReportRepository.getTotalAmountPerCustomer();
    Assert.notEmpty(result, "Array is empty");
    Assertions.assertEquals(BigInteger.valueOf(TEST_DATA_CUSTOMER_ID), result.get(0)[0]);
    Assertions.assertEquals(BigDecimal.valueOf(TEST_DATA_TOTAL_AMOUNT).toBigInteger(), ((BigDecimal) result.get(0)[1]).toBigInteger());
  }

}
