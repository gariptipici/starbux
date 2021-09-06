package com.bestseller.starbux.shop.service;

import static org.mockito.ArgumentMatchers.any;

import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.exception.CustomerNotfoundException;
import com.bestseller.starbux.shop.mapper.CustomerMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.model.Customer;
import com.bestseller.starbux.shop.model.Order;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.CustomerRepository;
import com.bestseller.starbux.shop.service.impl.CustomerServiceImpl;
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
class CustomerServiceTest {

  CustomerService customerService;

  Order order;
  CartItem cartItem;
  Customer customer;
  Product product;
  SideProduct sideProduct;
  Cart cart;
  @Mock
  CustomerRepository customerRepository;
  @Mock
  CartRepository cartRepository;
  final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @BeforeEach
  public void init() {
    customerService = new CustomerServiceImpl(customerRepository, cartRepository);

    product = new Product();
    product.setProductName("testName");
    product.setPrice(BigDecimal.TEN);
    product.setId(1L);

    sideProduct = new SideProduct();
    sideProduct.setPrice(BigDecimal.ONE);
    sideProduct.setProductName("testSideName");

    customer = new Customer();
    customer.setId(1L);

    cartItem = new CartItem();
    cartItem.setPrice(BigDecimal.TEN);
    cartItem.setQuantity(1);
    cartItem.setId(1L);
    cartItem.setProduct(product);
    cartItem.setSideProducts(Collections.singletonList(sideProduct));

    order = new Order();
    order.setAmount(BigDecimal.TEN);
    order.setDiscount(BigDecimal.ONE);
    order.setId(1L);
    order.setCartItems(Collections.singletonList(cartItem));

    cart = new Cart();
    cart.setId(1L);
    cart.setAmount(BigDecimal.TEN);
    cart.setDiscount(BigDecimal.ONE);

    customer.setCart(cart);
    cart.setCustomer(customer);
  }

  @Test
  void createCustomerTest() {
    Cart emptyCart = new Cart();
    emptyCart.setAmount(BigDecimal.ZERO);
    Mockito.when(cartRepository.save(any())).thenReturn(cart);
    Mockito.when(customerRepository.save(any())).thenReturn(customer);

    CustomerDto expected = customerMapper.customerToCustomerDto(customer);
    CustomerDto actual = customerService.createCustomer(expected);
    Assertions.assertNull(actual.getCart().getCartItems());
    Assertions.assertEquals(expected.getId(), actual.getId());
  }

  @Test
  void readCustomerTest() {
    Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
    CustomerDto actual = customerService.readCustomer(1L);
    Assertions.assertEquals(customer.getId(), actual.getId());
  }

  @Test
  void readCustomerNotFoundTest() {
    Mockito.when(customerRepository.findById(1L)).thenThrow(CustomerNotfoundException.class);
    Assertions
        .assertThrows(CustomerNotfoundException.class, () -> customerService.readCustomer(1L));
  }

}
