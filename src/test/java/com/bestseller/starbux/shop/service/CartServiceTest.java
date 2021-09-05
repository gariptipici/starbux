package com.bestseller.starbux.shop.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.repository.ProductRepository;
import com.bestseller.starbux.admin.repository.SideProductRepository;
import com.bestseller.starbux.common.dto.ProductDto;
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
import com.bestseller.starbux.shop.repository.CartItemRepository;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.OrderRepository;
import com.bestseller.starbux.shop.service.impl.CartServiceImpl;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTest {

  @Mock
  CartRepository cartRepository;
  @Mock
  OrderRepository orderRepository;
  @Mock
  CartItemRepository cartItemRepository;
  @Mock
  ProductRepository productRepository;
  @Mock
  SideProductRepository sideProductRepository;
  @Mock
  DiscountService discountService;

  CartService cartService;

  CartMapper cartMapper = CartMapper.INSTANCE;
  OrderMapper orderMapper = OrderMapper.INSTANCE;

  CartItem cartItem;
  Cart cart;
  Customer customer;
  Product product;
  SideProduct sideProduct;

  @BeforeEach
  public void init() {
    cartService = new CartServiceImpl(cartRepository, orderRepository, cartItemRepository,
        productRepository, sideProductRepository, discountService);

    product = new Product();
    product.setProductName("testName");
    product.setPrice(BigDecimal.TEN);
    product.setId(1L);

    sideProduct = new SideProduct();
    sideProduct.setPrice(BigDecimal.ONE);
    sideProduct.setProductName("testSideName");

    customer = new Customer();
    customer.setId(1L);

    cart = new Cart();
    cart.setId(1L);
    cart.setAmount(BigDecimal.TEN);
    cart.setDiscount(BigDecimal.ONE);

    cartItem = new CartItem();
    cartItem.setPrice(BigDecimal.TEN);
    cartItem.setQuantity(1);
    cartItem.setId(1L);
    cartItem.setProduct(product);
    cartItem.setSideProducts(Collections.singletonList(sideProduct));

    cart.setCartItems(Collections.singletonList(cartItem));

    customer.setCart(cart);
    cart.setCustomer(customer);
  }

  @Test
  public void readCartTest() {
    Mockito.when(cartRepository.findById_AndCustomerId(anyLong(), anyLong()))
        .thenReturn(Optional.of(cart));

    CartDto expected = cartMapper.cartToCartDto(cart);
    CartDto actual = cartService.readCart(1L, 1L);
    Assertions.assertEquals(expected.getCartItems().get(0).getProduct().getProductName(),
        actual.getCartItems().get(0).getProduct().getProductName());
  }

  @Test
  public void readCartTestNotFound() {
    Mockito.when(cartRepository.findById_AndCustomerId(anyLong(), anyLong()))
        .thenThrow(CartNotfoundException.class);

    Assertions.assertThrows(CartNotfoundException.class, () -> cartService.readCart(1L, 1L));
  }

  @Test
  public void updateCartTest() {
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenReturn(Optional.of(cart));
    ProductDto newProductDto = new ProductDto();
    newProductDto.setId(2L);
    newProductDto.setProductName("newName");
    newProductDto.setPrice(BigDecimal.TEN);
    Product newProduct = new Product();
    newProduct.setId(2L);
    newProduct.setProductName("newName");
    newProduct.setPrice(BigDecimal.TEN);
    Mockito.when(productRepository.findById(2L))
        .thenReturn(Optional.of(newProduct));
    Mockito.when(sideProductRepository.findAllById(anyList()))
        .thenReturn(Collections.singletonList(sideProduct));
    Mockito.when(discountService.calculateDiscount(any()))
        .thenReturn(BigDecimal.ZERO);
    Mockito.when(cartRepository.save(any()))
        .thenReturn(cart);

    CartDto expected = cartMapper.cartToCartDto(cart);

    expected.getCartItems().get(0).setProduct(newProductDto);
    CartDto actual = cartService.updateCart(1L, 1L, expected);
    Assertions.assertEquals(expected.getCartItems().get(0).getProduct().getProductName(),
        actual.getCartItems().get(0).getProduct().getProductName());
  }

  @Test
  public void updateCartTestProductNotFound() {
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenReturn(Optional.of(cart));
    ProductDto newProductDto = new ProductDto();
    newProductDto.setId(2L);
    newProductDto.setProductName("newName");
    newProductDto.setPrice(BigDecimal.TEN);
    Product newProduct = new Product();
    newProduct.setId(2L);
    newProduct.setProductName("newName");
    newProduct.setPrice(BigDecimal.TEN);
    Mockito.when(productRepository.findById(2L))
        .thenThrow(ProductNotFoundException.class);
    Mockito.when(sideProductRepository.findAllById(anyList()))
        .thenReturn(Collections.singletonList(sideProduct));
    Mockito.when(discountService.calculateDiscount(any()))
        .thenReturn(BigDecimal.ZERO);
    Mockito.when(cartRepository.save(any()))
        .thenReturn(cart);

    CartDto expected = cartMapper.cartToCartDto(cart);

    expected.getCartItems().get(0).setProduct(newProductDto);
    Assertions.assertThrows(ProductNotFoundException.class,
        () -> cartService.updateCart(1L, 1L, expected));
  }

  @Test
  public void updateCartTestCartNotFound() {
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenThrow(CartNotfoundException.class);
    ProductDto newProductDto = new ProductDto();
    newProductDto.setId(2L);
    newProductDto.setProductName("newName");
    newProductDto.setPrice(BigDecimal.TEN);
    Product newProduct = new Product();
    newProduct.setId(2L);
    newProduct.setProductName("newName");
    newProduct.setPrice(BigDecimal.TEN);
    Mockito.when(productRepository.findById(2L))
        .thenReturn(Optional.of(newProduct));
    Mockito.when(sideProductRepository.findAllById(anyList()))
        .thenReturn(Collections.singletonList(sideProduct));
    Mockito.when(discountService.calculateDiscount(any()))
        .thenReturn(BigDecimal.ZERO);
    Mockito.when(cartRepository.save(any()))
        .thenReturn(cart);

    CartDto expected = cartMapper.cartToCartDto(cart);

    expected.getCartItems().get(0).setProduct(newProductDto);
    Assertions
        .assertThrows(CartNotfoundException.class, () -> cartService.updateCart(1L, 1L, expected));
  }

  @Test
  public void emptyCartTest() {
    cart.setCartItems(Collections.singletonList(cartItem));
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenReturn(Optional.of(cart));
    Assertions.assertFalse(cart.getCartItems().isEmpty());
    CartDto actual = cartService.emptyCart(1L, 1L);
    Assertions.assertTrue(actual.getCartItems().isEmpty());
  }

  @Test
  public void emptyCartTestNotFound() {
    cart.setCartItems(Collections.singletonList(cartItem));
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenThrow(CartNotfoundException.class);
    Assertions.assertThrows(CartNotfoundException.class, () -> cartService.emptyCart(1L, 1L));
  }

  @Test
  public void checkoutCartTest() {
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenReturn(Optional.of(cart));
    OrderDto expected = orderMapper.orderToOrderDto(cartMapper.cartToOrder(cart));
    Mockito.when(orderRepository.save(any())).thenReturn(cartMapper.cartToOrder(cart));
    Mockito.when(cartRepository.save(any())).thenReturn(new Cart());

    OrderDto actual = cartService.checkoutCart(1L, 1L);
    Assertions.assertEquals(expected.getCartItems().get(0).getProduct().getProductName(),
        actual.getCartItems().get(0).getProduct().getProductName());
  }

  @Test
  public void checkoutCartNotFoundTest() {
    Mockito.when(cartRepository.findById_AndCustomerId(1L, 1L))
        .thenThrow(CartNotfoundException.class);
    OrderDto expected = orderMapper.orderToOrderDto(cartMapper.cartToOrder(cart));
    Mockito.when(orderRepository.save(any())).thenReturn(cartMapper.cartToOrder(cart));
    Mockito.when(cartRepository.save(any())).thenReturn(new Cart());

    Assertions.assertThrows(CartNotfoundException.class, () -> cartService.checkoutCart(1L, 1L));
  }

}
