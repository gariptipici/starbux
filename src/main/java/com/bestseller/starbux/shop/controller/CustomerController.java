package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{customerId}")
    public CustomerDto readCustomer(@PathVariable Long customerId){
        return customerService.readCustomer(customerId);
    }
}
