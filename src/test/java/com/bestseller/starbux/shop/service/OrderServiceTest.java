package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.exception.OrderNotfoundException;

import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.model.Customer;
import com.bestseller.starbux.shop.model.Order;
import com.bestseller.starbux.shop.repository.OrderRepository;
import com.bestseller.starbux.shop.service.impl.OrderServiceImpl;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

  OrderService orderService;
  Order order;
  CartItem cartItem;
  Customer customer;
  Product product;
  SideProduct sideProduct;

  @Mock
  OrderRepository orderRepository;

  @BeforeEach
  public void init() {
    orderService = new OrderServiceImpl(orderRepository);
    product = new Product();
    product.setProductName("testName");
    product.setPrice(BigDecimal.TEN);
    product.setId(1L);

    sideProduct = new SideProduct();
    sideProduct.setPrice(BigDecimal.ONE);
    sideProduct.setProductName("testSideName");

    customer = new Customer();
    customer.setId(1L);

    cartItem = new CartItem();
    cartItem.setPrice(BigDecimal.TEN);
    cartItem.setQuantity(1);
    cartItem.setId(1L);
    cartItem.setProduct(product);
    cartItem.setSideProducts(Collections.singletonList(sideProduct));

    order = new Order();
    order.setAmount(BigDecimal.TEN);
    order.setDiscount(BigDecimal.ONE);
    order.setId(1L);
    order.setCartItems(Collections.singletonList(cartItem));
  }

  @Test
  void readOrderTest() {
    Mockito.when(orderRepository.findById_AndCustomerId(1L, 1L)).thenReturn(Optional.of(order));
    OrderDto actual = orderService.readOrder(1L, 1L);
    Assertions.assertEquals(order.getAmount(), actual.getAmount());
  }

  @Test
  void readOrderNotFoundTest() {
    Mockito.when(orderRepository.findById_AndCustomerId(1L, 1L))
        .thenThrow(OrderNotfoundException.class);
    Assertions.assertThrows(OrderNotfoundException.class, () -> orderService.readOrder(1L, 1L));
  }

  @Test
  void readOrdersTest() {
    Mockito.when(orderRepository.findAllByCustomerId(1L))
        .thenReturn(Collections.singletonList(order));

    List<OrderDto> actual = orderService.readOrders(1L);
    Assertions.assertEquals(order.getAmount(), actual.get(0).getAmount());
  }

}
