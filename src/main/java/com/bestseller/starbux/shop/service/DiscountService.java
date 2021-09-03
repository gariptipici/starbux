package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.shop.model.Cart;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal calculateDiscount(Cart cart);
}
