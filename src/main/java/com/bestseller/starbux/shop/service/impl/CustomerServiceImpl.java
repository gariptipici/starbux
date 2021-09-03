package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.exception.CustomerNotfoundException;
import com.bestseller.starbux.shop.mapper.CustomerMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.Customer;
import com.bestseller.starbux.shop.repository.CartItemRepository;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.CustomerRepository;
import com.bestseller.starbux.shop.service.CustomerService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        Cart emptyCart = new Cart();
        emptyCart.setAmount(BigDecimal.ZERO);
        Cart cart = cartRepository.save(emptyCart);
        customer.setCart(cart);
        cart.setCustomer(customer);
        return customerMapper.customerToCustomerDto(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto readCustomer(Long customerId) {
        return customerMapper.customerToCustomerDto(
                customerRepository.findById(customerId).orElseThrow(CustomerNotfoundException::new));
    }

    @Override
    @Modifying
    @Transactional
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer updated = customerRepository.findById(customerId).map(existing -> {
            Customer update = customerMapper.customerDtoToCustomer(customerDto);
            update.setId(existing.getId());
            return update;
        }).orElseThrow(CustomerNotfoundException::new);

        return customerMapper.customerToCustomerDto(updated);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
