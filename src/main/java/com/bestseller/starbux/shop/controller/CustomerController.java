package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CustomerDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/customers")
public class CustomerController {

    @PostMapping(consumes = "application/json")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto){
        return null;
    }

    @GetMapping(consumes = "application/json")
    public CustomerDto readCustomer(@PathVariable Long customerId){
        return null;
    }
}
