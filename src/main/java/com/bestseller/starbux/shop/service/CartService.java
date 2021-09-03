package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.shop.dto.CartDto;


public interface CartService {

    CartDto readCart(Long customerId, Long cartId);

    CartDto updateCart(Long customerId, Long cartId, CartDto cartDto);

    CartDto emptyCart(Long customerId, Long cartId);
}
