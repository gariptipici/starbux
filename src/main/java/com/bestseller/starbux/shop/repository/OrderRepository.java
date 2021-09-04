package com.bestseller.starbux.shop.repository;

import com.bestseller.starbux.shop.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
  Optional<Order> findById_AndCustomerId(Long orderId, Long customerId);

  List<Order> findAllByCustomerId(Long customerId);
}
