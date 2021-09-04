package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.dto.OrderDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface CartService {

    CartDto readCart(Long customerId, Long cartId);

    CartDto updateCart(Long customerId, Long cartId, CartDto cartDto);

    CartDto emptyCart(Long customerId, Long cartId);

  @Transactional
  @Modifying
  OrderDto checkoutCart(Long customerId, Long cartId);
}
