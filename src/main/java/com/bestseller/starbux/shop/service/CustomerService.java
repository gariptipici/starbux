package com.bestseller.starbux.shop.service;

import com.bestseller.starbux.shop.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto readCustomer(Long customerId);
}
