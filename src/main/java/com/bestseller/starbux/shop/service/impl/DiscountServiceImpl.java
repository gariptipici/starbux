package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.service.DiscountService;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

  private final Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

  private static final BigDecimal PRICE_THRESHOLD = BigDecimal.valueOf(12);
  private static final Integer QUANTITY_THRESHOLD = 3;

  @Override
  public BigDecimal calculateDiscount(Cart cart) {
    logger.debug("Calculating discount for cart with cart id {}", cart.getId());
    BigDecimal totalAmount = cart.getCartItems().stream().map(CartItem::getPrice)
        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

    BigDecimal percentageDiscount = totalAmount.compareTo(PRICE_THRESHOLD) >= 0 ? totalAmount
        .divide(BigDecimal.valueOf(4.0), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;

    logger.debug(
        "Total amount of cart with cart id {} is {}, price threshold is {} so applicable discount is {}",
        cart.getId(), totalAmount, PRICE_THRESHOLD, percentageDiscount);

    int quantity = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
    CartItem minPriceItem = cart.getCartItems().stream().min(Comparator.comparing(
        cartItem -> cartItem.getPrice()
            .divide(BigDecimal.valueOf(cartItem.getQuantity()), 2, RoundingMode.HALF_UP)))
        .orElse(new CartItem());

    BigDecimal lowestPricedItem = minPriceItem.getPrice()
        .divide(BigDecimal.valueOf(minPriceItem.getQuantity()), 2, RoundingMode.HALF_UP);
    BigDecimal quantityDiscount = quantity >= QUANTITY_THRESHOLD ? lowestPricedItem
        : BigDecimal.ZERO;

    logger.debug(
        "Total product quantity of cart with cart id {} is {}, quantity threshold is {} so applicable discount is {} because lowest priced product is {}",
        cart.getId(), quantity, QUANTITY_THRESHOLD, quantityDiscount, lowestPricedItem);

    BigDecimal appliedDiscount = percentageDiscount.compareTo(quantityDiscount) >= 0 ?
        percentageDiscount : quantityDiscount;
    logger.debug("Applied discount for cart with cart id {} is {}", cart.getId(), appliedDiscount);
    return appliedDiscount;
  }
}
