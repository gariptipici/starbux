package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.repository.CustomerInfoReportRepository;
import com.bestseller.starbux.admin.service.impl.CustomerInfoReportServiceImpl;
import com.bestseller.starbux.shop.repository.CartItemRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerInfoReportServiceTest {

  CustomerInfoReportService customerInfoReportService;
  CartItemRepository cartItemRepository;

  List<Object[]> reportResult;

  @Mock
  CustomerInfoReportRepository customerInfoReportRepository;

  @BeforeEach
  public void init() {
    customerInfoReportService = new CustomerInfoReportServiceImpl(customerInfoReportRepository,
        cartItemRepository);
    reportResult = Collections.singletonList(new Object[]{BigInteger.ONE, BigDecimal.TEN});
    Mockito.when(customerInfoReportRepository.getTotalAmountPerCustomer()).thenReturn(reportResult);
  }

  @Test
  public void getTotalAmountPerCustomerTest(){
    List<CustomerInfoDto> result = customerInfoReportService.getTotalAmountPerCustomer();
    Assertions.assertEquals(result.get(0).getCustomerId(), 1L);
    Assertions.assertEquals(result.get(0).getOrderTotalAmount(), BigDecimal.TEN);
  }
}
