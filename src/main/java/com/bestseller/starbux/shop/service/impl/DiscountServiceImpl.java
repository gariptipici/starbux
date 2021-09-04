package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.service.DiscountService;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.Comparator;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

  private static final BigDecimal PRICE_THRESHOLD = BigDecimal.valueOf(12);
  private static final Integer QUANTITY_THRESHOLD = 3;

  @Override
  public BigDecimal calculateDiscount(Cart cart) {
    BigDecimal totalAmount = cart.getCartItems().stream().map(CartItem::getPrice)
        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

    BigDecimal percentageDiscount = totalAmount.compareTo(PRICE_THRESHOLD) >= 0 ? totalAmount
        .divide(BigDecimal.valueOf(4.0), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    int quantity = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
    CartItem minPriceItem = cart.getCartItems().stream().min(Comparator.comparing(
        cartItem -> cartItem.getPrice()
            .divide(BigDecimal.valueOf(cartItem.getQuantity()), 2, RoundingMode.HALF_UP)))
        .orElse(new CartItem());

    BigDecimal quantityDiscount = quantity >= QUANTITY_THRESHOLD ? minPriceItem.getPrice()
        .divide(BigDecimal.valueOf(minPriceItem.getQuantity()), 2, RoundingMode.HALF_UP)
        : BigDecimal.ZERO;
    return percentageDiscount.compareTo(quantityDiscount) >= 0 ?
        percentageDiscount : quantityDiscount;
  }
}
