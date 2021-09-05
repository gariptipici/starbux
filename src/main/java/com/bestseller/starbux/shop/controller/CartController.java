package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.shop.dto.CartDto;

import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/customers/{customerId}/carts")
@Api("Set of endpoints for Retrieving, Updating and Emptying and Checkout of carts.")
public class CartController {

  public final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/{cartId}")
  @ApiOperation("Retrieves the cart information.")
  @ResponseStatus(HttpStatus.OK)
  public CartDto readCart(
      @ApiParam(value = "Id of the customer that owns the cart to be retrieved. Cannot be empty.", example = "1") @PathVariable Long customerId,
      @ApiParam(value = "Id of the product to be retrieved. Cannot be empty.", example = "1") @PathVariable Long cartId) {
    return cartService.readCart(customerId, cartId);
  }

  @PutMapping("/{cartId}")
  @ApiOperation("Updates the cart information.")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public CartDto updateCart(
      @ApiParam(value = "Id of the customer that owns the cart to be retrieved. Cannot be empty.", example = "1") @PathVariable Long customerId,
      @ApiParam(value = "Id of the product to be retrieved. Cannot be empty.", example = "1") @PathVariable Long cartId,
      @ApiParam("New cart information for a the cart to be updated.") @RequestBody CartDto cartDto) {
    return cartService.updateCart(customerId, cartId, cartDto);
  }

  @PostMapping("/{cartId}/checkout")
  @ApiOperation("Checks out the cart information.")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto createOrder(
      @ApiParam(value = "Id of the customer that owns the cart to be checked out. Cannot be empty.", example = "1") @PathVariable Long customerId,
      @ApiParam(value = "Id of the cart to be checked out. Cannot be empty.", example = "1") @PathVariable Long cartId) {
    return cartService.checkoutCart(customerId, cartId);
  }

  @DeleteMapping("/{cartId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  @ApiOperation("Deletes the cart information.")
  public CartDto deleteCart(
      @ApiParam(value = "Id of the customer that owns the cart to be deleted. Cannot be empty.", example = "1") @PathVariable Long customerId,
      @ApiParam(value = "Id of the cart to be deleted. Cannot be empty.", example = "1") @PathVariable Long cartId) {
    return cartService.emptyCart(customerId, cartId);
  }
}
