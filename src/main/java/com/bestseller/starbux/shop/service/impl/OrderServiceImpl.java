package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.exception.OrderNotfoundException;
import com.bestseller.starbux.shop.mapper.OrderMapper;
import com.bestseller.starbux.shop.repository.OrderRepository;
import com.bestseller.starbux.shop.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  private static final OrderMapper orderMapper = OrderMapper.INSTANCE;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public OrderDto readOrder(Long customerId, Long orderId) {
    return orderMapper.orderToOrderDto(
        orderRepository.findById_AndCustomerId(orderId, customerId)
            .orElseThrow(OrderNotfoundException::new));
  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderDto> readOrders(Long customerId) {
    return orderMapper.orderListToOrderDtoList(orderRepository.findAllByCustomerId(customerId));
  }
}
