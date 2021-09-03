package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.exception.CartNotfoundException;
import com.bestseller.starbux.shop.mapper.CartMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.repository.CartRepository;

public class CartServiceImpl {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDto readCart(Long customerId, Long cartId) {
        return cartMapper.cartToCartDto(
                cartRepository.findByCustomer_Id_AndId(customerId, cartId).orElseThrow(CartNotfoundException::new));
    }

    public CartDto updateCart(Long customerId, Long cartId, CartDto cartDto) {
        Cart updated = cartRepository.findByCustomer_Id_AndId(customerId, cartId).map(existing -> {
            Cart update = cartMapper.cartDtoToCart(cartDto);
            update.setId(existing.getId());
            return update;
        }).orElseThrow(CartNotfoundException::new);

        return cartMapper.cartToCartDto(updated);
    }

    public CartDto emptyCart(Long customerId, Long cartId) {
        Cart updated = cartRepository.findByCustomer_Id_AndId(customerId, cartId).map(existing -> {
            Cart empty = new Cart();
            empty.setId(existing.getId());
            return empty;
        }).orElseThrow(CartNotfoundException::new);

        return cartMapper.cartToCartDto(updated);
    }
}
