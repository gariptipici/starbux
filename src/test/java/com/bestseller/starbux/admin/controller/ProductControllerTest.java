package com.bestseller.starbux.admin.controller;


import static org.mockito.ArgumentMatchers.any;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.ProductDto;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductService productService;

  ProductDto productDto;

  @BeforeEach
  public void init() {
    productDto = new ProductDto();
    productDto.setProductName("productName");
    productDto.setPrice(BigDecimal.TEN);
    productDto.setId(1L);
  }

  @Test
  public void createProductTest() throws Exception {
    Mockito.when(productService.createProduct(any())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(post("/admin/products").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isCreated())
        .andReturn();
    ProductDto actual = TestUtil.parseResponse(result, ProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void readProductTest() throws Exception {
    Mockito.when(productService.readProduct(anyLong())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(get("/admin/products/1")).andDo(print()).andExpect(status().isOk())
        .andReturn();
    ProductDto actual = TestUtil.parseResponse(result, ProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void readProductNotFoundTest() throws Exception {
    Mockito.when(productService.readProduct(anyLong())).thenThrow(ProductNotFoundException.class);

    mockMvc.perform(get("/admin/products/1")).andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  public void readProductsTest() throws Exception {
    Mockito.when(productService.readProduct(anyLong())).thenReturn(productDto);

    mockMvc.perform(get("/admin/products")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void updateProductTest() throws Exception {
    Mockito.when(productService.updateProduct(anyLong(), any())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(put("/admin/products/1").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isAccepted())
        .andReturn();
    ProductDto actual = TestUtil.parseResponse(result, ProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void updateProductNotFoundTest() throws Exception {
    Mockito.when(productService.updateProduct(anyLong(), any()))
        .thenThrow(ProductNotFoundException.class);

    mockMvc
        .perform(put("/admin/products/1").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isNotFound());

  }

  @Test
  public void deleteProductTest() throws Exception {

    mockMvc.perform(delete("/admin/products/1")).andDo(print()).andExpect(status().isNoContent());
  }


}
