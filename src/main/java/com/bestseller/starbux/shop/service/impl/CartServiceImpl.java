package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.repository.ProductRepository;
import com.bestseller.starbux.admin.repository.SideProductRepository;
import com.bestseller.starbux.common.dto.BaseDto;
import com.bestseller.starbux.common.model.AbstractProduct;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.exception.CartNotfoundException;
import com.bestseller.starbux.shop.mapper.CartMapper;
import com.bestseller.starbux.shop.mapper.OrderMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.model.Customer;
import com.bestseller.starbux.shop.model.Order;
import com.bestseller.starbux.shop.repository.CartItemRepository;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.CustomerRepository;
import com.bestseller.starbux.shop.repository.OrderRepository;
import com.bestseller.starbux.shop.service.CartService;
import com.bestseller.starbux.shop.service.DiscountService;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final SideProductRepository sideProductRepository;
  private final DiscountService discountService;
  private final CustomerRepository customerRepository;
  private final CartMapper cartMapper = CartMapper.INSTANCE;
  private final OrderMapper orderMapper = OrderMapper.INSTANCE;

  public CartServiceImpl(CartRepository cartRepository,
      OrderRepository orderRepository,
      CartItemRepository cartItemRepository,
      ProductRepository productRepository, SideProductRepository sideProductRepository,
      DiscountService discountService,
      CustomerRepository customerRepository) {
    this.cartRepository = cartRepository;
    this.orderRepository = orderRepository;
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
    this.sideProductRepository = sideProductRepository;
    this.discountService = discountService;
    this.customerRepository = customerRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public CartDto readCart(Long customerId, Long cartId) {
    return cartMapper.cartToCartDto(
        cartRepository.findById_AndCustomerId(cartId, customerId)
            .orElseThrow(CartNotfoundException::new));
  }

  @Override
  @Transactional
  @Modifying
  public CartDto updateCart(Long customerId, Long cartId, CartDto cartDto) {
    Cart updated = cartRepository.findById_AndCustomerId(cartId, customerId).map(existingCart -> {
      List<CartItem> newItems = cartDto.getCartItems().stream().map(itemDto -> {
        CartItem item = new CartItem();
        Product product = productRepository.findById(itemDto.getProduct().getId())
            .orElseThrow(ProductNotFoundException::new);
        item.setProduct(product);
        List<Long> sideProductIds = itemDto.getSideProducts().stream().map(BaseDto::getId)
            .collect(Collectors.toList());
        Iterable<SideProduct> sideProducts = sideProductRepository.findAllById(sideProductIds);
        item.setSideProducts(StreamSupport.stream(sideProducts.spliterator(), false)
            .collect(Collectors.toList()));
        item.setQuantity(itemDto.getQuantity());
        BigDecimal sidePrice = item.getSideProducts().stream()
            .map(AbstractProduct::getPrice)
            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal price = item.getProduct().getPrice();
        BigDecimal totalPrice = BigDecimal.valueOf(item.getQuantity())
            .multiply(sidePrice.add(price));

        item.setPrice(totalPrice);
        return item;
      }).collect(Collectors.toList());
      cartItemRepository.deleteAll(existingCart.getCartItems());
      existingCart.setCartItems(newItems);
      BigDecimal totalAmount = existingCart.getCartItems().stream().map(CartItem::getPrice)
          .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
      existingCart.setAmount(totalAmount);
      BigDecimal discount = discountService.calculateDiscount(existingCart);
      existingCart.setDiscount(discount);
      return existingCart;
    }).orElseThrow(CartNotfoundException::new);

    Cart updatedCart = cartRepository.save(updated);
    return cartMapper.cartToCartDto(updatedCart);
  }

  @Override
  @Transactional
  public CartDto emptyCart(Long customerId, Long cartId) {
    cartRepository.findById_AndCustomerId(cartId, customerId).ifPresent(existing -> {
      cartItemRepository.deleteAll(existing.getCartItems());
      existing.setCartItems(new ArrayList<>());
    });

    return readCart(customerId, cartId);
  }

  @Override
  @Transactional
  @Modifying
  public OrderDto checkoutCart(Long customerId, Long cartId) {
    Cart cart = cartRepository.findById_AndCustomerId(cartId, customerId)
        .orElseThrow(CartNotfoundException::new);
    Customer customer = cart.getCustomer();
    Order order = orderRepository.save(cartMapper.cartToOrder(cart));
    Cart emptyCart = new Cart();
    emptyCart.setAmount(BigDecimal.ZERO);
    emptyCart.setCustomer(customer);
    emptyCart = cartRepository.save(emptyCart);
    emptyCart.setCartItems(new ArrayList<>());
    cartRepository.delete(cart);

    return orderMapper.orderToOrderDto(order);
  }
}
