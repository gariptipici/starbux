package com.bestseller.starbux.shop.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bestseller.starbux.shop.dto.OrderDto;
import com.bestseller.starbux.shop.exception.OrderNotfoundException;
import com.bestseller.starbux.shop.service.OrderService;
import com.bestseller.starbux.testutil.TestUtil;
import java.math.BigDecimal;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  OrderService orderService;

  OrderDto orderDto;

  @BeforeEach
  public void init() {
    orderDto = new OrderDto();
    orderDto.setAmount(BigDecimal.TEN);
  }

  @Test
  void readOrderTest() throws Exception {
    Mockito.when(orderService.readOrder(anyLong(), anyLong())).thenReturn(orderDto);
    MvcResult result = mockMvc.perform(get("/shop/customers/1/orders/1")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    OrderDto actual = TestUtil.parseResponse(result, OrderDto.class);
    Assertions.assertEquals(orderDto.getAmount(), actual.getAmount());
  }

  @Test
  void readOrderNotFoundTest() throws Exception {
    Mockito.when(orderService.readOrder(anyLong(), anyLong()))
        .thenThrow(OrderNotfoundException.class);
    mockMvc.perform(get("/shop/customers/1/orders/1")).andDo(print())
        .andExpect(status().isNotFound()).andReturn();

  }

  @Test
  void readOrdersTest() throws Exception {
    Mockito.when(orderService.readOrders(anyLong()))
        .thenReturn(Collections.singletonList(orderDto));
    mockMvc.perform(get("/shop/customers/1/orders")).andDo(print())
        .andExpect(status().isOk());
  }

}
