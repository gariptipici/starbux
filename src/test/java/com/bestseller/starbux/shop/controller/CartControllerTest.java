package com.bestseller.starbux.shop.controller;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.exception.CartNotfoundException;
import com.bestseller.starbux.shop.service.CartService;
import com.bestseller.starbux.testutil.TestUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CartService cartService;

  OrderDto orderDto;
  CartDto cartDto;
  CartDto newCartDto;

  @BeforeEach
  public void init() {
    orderDto = new OrderDto();
    orderDto.setAmount(BigDecimal.ONE);
    cartDto = new CartDto();
    newCartDto = new CartDto();
    cartDto.setAmount(BigDecimal.TEN);

  }

  @Test
  void readCartTest() throws Exception {
    Mockito.when(cartService.readCart(1L, 1L)).thenReturn(cartDto);
    Mockito.when(cartService.readCart(2L, 1L)).thenThrow(CartNotfoundException.class);

    MvcResult result = mockMvc.perform(get("/shop/customers/1/carts/1")).andDo(print())
        .andExpect(status().isOk()).andReturn();
    mockMvc.perform(get("/shop/customers/2/carts/1")).andDo(print())
        .andExpect(status().isNotFound());

    CartDto actual = TestUtil.parseResponse(result, CartDto.class);
    Assertions.assertEquals(cartDto.getAmount(), actual.getAmount());
  }

  @Test
  void updateCartTest() throws Exception {
    newCartDto.setAmount(BigDecimal.ONE);
    Mockito.when(cartService.updateCart(anyLong(), anyLong(), any())).thenReturn(newCartDto);

    MvcResult result = mockMvc.perform(
        put("/shop/customers/1/carts/1").contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.requestBody(newCartDto))).andDo(print())
        .andExpect(status().isAccepted()).andReturn();
    CartDto actual = TestUtil.parseResponse(result, CartDto.class);
    Assertions.assertEquals(newCartDto.getAmount(), actual.getAmount());
  }

  @Test
 void updateCartCartNotFoundTest() throws Exception {
    newCartDto.setAmount(BigDecimal.ONE);
    Mockito.when(cartService.updateCart(anyLong(), anyLong(), any())).thenThrow(CartNotfoundException.class);
    mockMvc.perform(
        put("/shop/customers/1/carts/2").contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.requestBody(newCartDto))).andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void updateCartProductNotFoundTest() throws Exception {
    Mockito.when(cartService.updateCart(anyLong(), anyLong(), any()))
        .thenThrow(ProductNotFoundException.class);

    mockMvc.perform(
        put("/shop/customers/1/carts/3").contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.requestBody(newCartDto))).andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void createOrderTest() throws Exception{
    Mockito.when(cartService.checkoutCart(1L, 1L)).thenReturn(orderDto);
    MvcResult result = mockMvc.perform(
        post("/shop/customers/1/carts/1/checkout").contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.requestBody(cartDto))).andDo(print())
        .andExpect(status().isCreated()).andReturn();

    OrderDto actual = TestUtil.parseResponse(result, OrderDto.class);
    Assertions.assertEquals(orderDto.getAmount(), actual.getAmount());
  }

  @Test
  void deleteOrderTest() throws Exception{

    Mockito.when(cartService.emptyCart(1L, 1L)).thenReturn(cartDto);

    MvcResult result = mockMvc.perform(
        delete("/shop/customers/1/carts/1")).andDo(print())
        .andExpect(status().isAccepted()).andReturn();

    CartDto actual = TestUtil.parseResponse(result, CartDto.class);
    Assertions.assertEquals(cartDto.getAmount(), actual.getAmount());
  }

}
