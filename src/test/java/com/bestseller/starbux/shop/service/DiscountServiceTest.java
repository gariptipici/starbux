package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.service.impl.DiscountServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiscountServiceTest {

  DiscountService discountService;

  CartItem cartItem;
  CartItem cartItem2;
  CartItem cartItem3;
  Cart cart;
  Product product;
  SideProduct sideProduct;
  List<CartItem> cartItems;

  @BeforeEach
  public void init() {
    discountService = new DiscountServiceImpl();

    product = new Product();
    product.setProductName("testName");
    product.setPrice(BigDecimal.TEN);
    product.setId(1L);

    sideProduct = new SideProduct();
    sideProduct.setPrice(BigDecimal.ONE);
    sideProduct.setProductName("testSideName");

    cart = new Cart();
    cart.setId(1L);
    cart.setAmount(BigDecimal.TEN);
    cart.setDiscount(BigDecimal.ONE);

    cartItem = new CartItem();
    cartItem.setPrice(BigDecimal.valueOf(8));
    cartItem.setQuantity(1);
    cartItem.setId(1L);
    cartItem.setProduct(product);
    cartItem.setSideProducts(Collections.singletonList(sideProduct));

    cartItem2 = new CartItem();
    cartItem2.setPrice(BigDecimal.TEN);
    cartItem2.setQuantity(1);
    cartItem2.setId(1L);
    cartItem2.setProduct(product);
    cartItem2.setSideProducts(Collections.singletonList(sideProduct));

    cartItem3 = new CartItem();
    cartItem3.setPrice(BigDecimal.valueOf(2));
    cartItem3.setQuantity(2);
    cartItem3.setId(1L);
    cartItem3.setProduct(product);
    cartItem3.setSideProducts(Collections.singletonList(sideProduct));

    cartItems = new ArrayList<>();
    cartItems.add(cartItem);
  }

  @Test
  void calculateDiscountNotEligibleAnyDiscountTest() {
    cart.setCartItems(cartItems);
    BigDecimal actualDiscount = discountService.calculateDiscount(cart);
    Assertions.assertEquals(BigDecimal.ZERO, actualDiscount);
  }

  @Test
  void calculateDiscountOnlyEligibleToPriceDiscountTest() {
    cartItems.add(cartItem2);
    cart.setCartItems(cartItems);
    BigDecimal actualDiscount = discountService.calculateDiscount(cart);
    Assertions.assertEquals(BigDecimal.valueOf(4.5).doubleValue(), actualDiscount.doubleValue());
  }

  @Test
  void calculateDiscountOnlyEligibleToQuantityDiscountTest() {
    cartItems.add(cartItem3);
    cart.setCartItems(cartItems);
    BigDecimal actualDiscount = discountService.calculateDiscount(cart);
    Assertions.assertEquals(BigDecimal.valueOf(1L).doubleValue(), actualDiscount.doubleValue());
  }

  @Test
  void calculateDiscountEligibleForBothDiscountTest() {
    cartItems.add(cartItem3);
    cartItems.add(cartItem2);
    cart.setCartItems(cartItems);
    BigDecimal actualDiscount = discountService.calculateDiscount(cart);
    Assertions.assertEquals(BigDecimal.valueOf(5L).doubleValue(), actualDiscount.doubleValue());
  }

}
