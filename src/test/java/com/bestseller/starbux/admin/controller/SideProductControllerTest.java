package com.bestseller.starbux.admin.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.common.dto.SideProductDto;
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

@WebMvcTest(SideProductController.class)
public class SideProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductService productService;

  SideProductDto productDto;

  @BeforeEach
  public void init() {
    productDto = new SideProductDto();
    productDto.setProductName("productName");
    productDto.setPrice(BigDecimal.TEN);
    productDto.setId(1L);
  }

  @Test
  public void createProductTest() throws Exception {
    Mockito.when(productService.createSideProduct(any())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(post("/admin/sideproducts").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isCreated())
        .andReturn();
    SideProductDto actual = TestUtil.parseResponse(result, SideProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void readProductTest() throws Exception {
    Mockito.when(productService.readSideProduct(anyLong())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(get("/admin/sideproducts/1")).andDo(print()).andExpect(status().isOk())
        .andReturn();
    SideProductDto actual = TestUtil.parseResponse(result, SideProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void readProductNotFoundTest() throws Exception {
    Mockito.when(productService.readSideProduct(anyLong())).thenThrow(ProductNotFoundException.class);

    mockMvc.perform(get("/admin/sideproducts/1")).andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  public void readProductsTest() throws Exception {
    Mockito.when(productService.readSideProduct(anyLong())).thenReturn(productDto);

    mockMvc.perform(get("/admin/sideproducts")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void updateProductTest() throws Exception {
    Mockito.when(productService.updateSideProduct(anyLong(), any())).thenReturn(productDto);

    MvcResult result = mockMvc
        .perform(put("/admin/sideproducts/1").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isAccepted())
        .andReturn();
    SideProductDto actual = TestUtil.parseResponse(result, SideProductDto.class);

    Assertions.assertEquals(productDto.getPrice(), actual.getPrice());
  }

  @Test
  public void updateProductNotFoundTest() throws Exception {
    Mockito.when(productService.updateSideProduct(anyLong(), any()))
        .thenThrow(ProductNotFoundException.class);

    mockMvc
        .perform(put("/admin/sideproducts/1").contentType(MediaType.APPLICATION_JSON).content(
            TestUtil.requestBody(productDto))).andDo(print()).andExpect(status().isNotFound());

  }

  @Test
  public void deleteProductTest() throws Exception {

    mockMvc.perform(delete("/admin/sideproducts/1")).andDo(print()).andExpect(status().isNoContent());
  }


}
