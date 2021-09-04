package com.bestseller.starbux.shop.mapper;

import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.model.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class OrderMapper {

  public static OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  public abstract OrderDto orderToOrderDto(Order order);
  public abstract List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
