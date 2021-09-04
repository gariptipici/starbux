package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop/customers/{customerId}/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public List<OrderDto> readOrders(@PathVariable Long customerId){
    return orderService.readOrders(customerId);
  }

  @GetMapping("/{orderId}")
  public OrderDto readOrder(@PathVariable Long customerId, @PathVariable Long orderId){
    return orderService.readOrder(customerId, orderId);
  }
}
