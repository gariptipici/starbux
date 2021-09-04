package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.shop.dto.OrderDto;
import java.util.List;

public interface OrderService {

  OrderDto readOrder(Long customerId, Long orderId);

  List<OrderDto> readOrders(Long customerId);
}
