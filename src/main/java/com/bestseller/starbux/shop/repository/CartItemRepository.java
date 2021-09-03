package com.bestseller.starbux.shop.repository;

import com.bestseller.starbux.shop.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
