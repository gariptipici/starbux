package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.service.DiscountService;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Override
    public BigDecimal calculateDiscount(Cart cart) {
        cart.getCartItems().stream().map(cartItem -> {
            cartItem.getPrice();
            return cartItem;
        }).collect(Collectors.toList());
        return null;
    }
}
