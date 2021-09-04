package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.exception.CustomerNotfoundException;
import com.bestseller.starbux.shop.mapper.CustomerMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.Customer;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.CustomerRepository;
import com.bestseller.starbux.shop.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {

  Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private final CustomerRepository customerRepository;
  private final CartRepository cartRepository;
  private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  public CustomerServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
    this.customerRepository = customerRepository;
    this.cartRepository = cartRepository;
  }

  @Override
  @Transactional
  public CustomerDto createCustomer(CustomerDto customerDto) {

    Customer customer = customerMapper.customerDtoToCustomer(customerDto);

    Cart emptyCart = new Cart();
    emptyCart.setAmount(BigDecimal.ZERO);

    logger.debug("Creating empty cart for new customer");
    Cart cart = cartRepository.save(emptyCart);
    logger.debug("Empty cart with id {} created for new customer.", cart.getId());

    customer.setCart(cart);
    cart.setCustomer(customer);

    logger.debug("Creating new customer...");
    customer = customerRepository.save(customer);
    logger.debug("Customer created with id {}", customer.getId());

    return customerMapper.customerToCustomerDto(customer);
  }

  @Override
  @Transactional(readOnly = true)
  public CustomerDto readCustomer(Long customerId) {
    return customerMapper.customerToCustomerDto(
        customerRepository.findById(customerId).orElseThrow(CustomerNotfoundException::new));
  }

}
