package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/customers")
@Api("Set of endpoints for Retrieving and Creating of customers.")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping(consumes = "application/json")
  @ApiOperation("Creates the customer information.")
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerDto createCustomer(
      @ApiParam("New customer information for a the customer to be created. It also creates an empty cart for the customer") @RequestBody CustomerDto customerDto) {
    return customerService.createCustomer(customerDto);
  }

  @GetMapping("/{customerId}")
  @ApiOperation("Retrieves the customer information.")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDto readCustomer(
      @ApiParam(value = "Id of the customer to be retrieved. Cannot be empty.", example = "1") @PathVariable Long customerId) {
    return customerService.readCustomer(customerId);
  }
}
