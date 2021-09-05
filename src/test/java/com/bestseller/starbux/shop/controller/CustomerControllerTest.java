package com.bestseller.starbux.shop.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.exception.CustomerNotfoundException;
import com.bestseller.starbux.shop.service.CustomerService;
import com.bestseller.starbux.testutil.TestUtil;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  CustomerDto customerDto;

  @MockBean
  CustomerService customerService;

  @BeforeEach
  public void init() {
    customerDto = new CustomerDto();
    customerDto.setId(1L);
  }

  @Test
  public void createCustomerTest() throws Exception {
    Mockito.when(customerService.createCustomer(any())).thenReturn(customerDto);
    MvcResult result = mockMvc
        .perform(post("/shop/customers").contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.requestBody(customerDto))).andDo(print())
        .andExpect(status().isCreated()).andReturn();

    CustomerDto actual = TestUtil.parseResponse(result, CustomerDto.class);
    Assertions.assertEquals(customerDto.getId(), actual.getId());
  }

  @Test
  public void readCustomerTest() throws Exception {
    Mockito.when(customerService.readCustomer(anyLong())).thenReturn(customerDto);
    MvcResult result = mockMvc.perform(get("/shop/customers/1")).andDo(print())
        .andExpect(status().isOk()).andReturn();

    CustomerDto actual = TestUtil.parseResponse(result, CustomerDto.class);
    Assertions.assertEquals(customerDto.getId(), actual.getId());
  }

  @Test
  public void readCustomerNotFoundTest() throws Exception {
    Mockito.when(customerService.readCustomer(anyLong()))
        .thenThrow(CustomerNotfoundException.class);
    MvcResult result = mockMvc.perform(get("/shop/customers/1")).andDo(print())
        .andExpect(status().isNotFound()).andReturn();

  }
}
