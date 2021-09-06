package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.dto.ProductInfoDto;
import com.bestseller.starbux.admin.repository.CustomerInfoReportRepository;
import com.bestseller.starbux.admin.service.impl.CustomerInfoReportServiceImpl;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.model.CartItem;
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
class CustomerInfoReportServiceTest {

  CustomerInfoReportService customerInfoReportService;

  List<Object[]> reportResult;

  CartItem cartItem;
  SideProduct sideProduct;
  Product product;

  @Mock
  CustomerInfoReportRepository customerInfoReportRepository;

  @Mock
  CartItemRepository cartItemRepository;

  @BeforeEach
  public void init() {
    customerInfoReportService = new CustomerInfoReportServiceImpl(customerInfoReportRepository,
        cartItemRepository);
    cartItem = new CartItem();
    product = new Product();
    product.setId(1L);
    cartItem.setProduct(product);

    sideProduct = new SideProduct();
    sideProduct.setId(1L);

    cartItem.setSideProducts(Collections.singletonList(sideProduct));
    reportResult = Collections.singletonList(new Object[]{BigInteger.ONE, BigDecimal.TEN});
    Mockito.when(customerInfoReportRepository.getTotalAmountPerCustomer()).thenReturn(reportResult);

    Mockito.when(cartItemRepository.findAll()).thenReturn(Collections.singletonList(cartItem));
  }

  @Test
  void getTotalAmountPerCustomerTest() {
    List<CustomerInfoDto> result = customerInfoReportService.getTotalAmountPerCustomer();
    Assertions.assertEquals(1L, result.get(0).getCustomerId());
    Assertions.assertEquals(BigDecimal.TEN, result.get(0).getOrderTotalAmount());
  }

  @Test
  void getMostUsedToppingPerProductTest() {
    List<ProductInfoDto> result = customerInfoReportService.getMostUsedToppingPerProduct();
    Assertions.assertEquals(1L, result.get(0).getProductId());
    Assertions.assertEquals(1L, result.get(0).getSideProductId());
  }
}
