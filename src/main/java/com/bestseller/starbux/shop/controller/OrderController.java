package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop/customers/{customerId}/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  @ApiOperation("Retrieves all of the orders information.")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDto> readOrders(
      @ApiParam(value = "Id of the customer whom orders to be retrieved. Cannot be empty.", example = "1") @PathVariable Long customerId) {
    return orderService.readOrders(customerId);
  }

  @GetMapping("/{orderId}")
  @ApiOperation("Retrieves the order information.")
  @ResponseStatus(HttpStatus.OK)
  public OrderDto readOrder(
      @ApiParam(value = "Id of the customer whom orders to be retrieved. Cannot be empty.", example = "1") @PathVariable Long customerId,
      @ApiParam(value = "Id of the order to be retrieved. Cannot be empty.", example = "1") @PathVariable Long orderId) {
    return orderService.readOrder(customerId, orderId);
  }
}
