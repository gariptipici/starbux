package com.bestseller.starbux.admin.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bestseller.starbux.admin.service.CustomerInfoReportService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportController.class)
 class ReportControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CustomerInfoReportService customerInfoReportService;

  @Test
   void getTotalAmountPerCustomerTest() throws Exception{
    Mockito.when(customerInfoReportService.getTotalAmountPerCustomer()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/admin/reports/orderCountByCustomer")).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
   void getMostUsedToppingPerProductTest() throws Exception{
    Mockito.when(customerInfoReportService.getMostUsedToppingPerProduct()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/admin/reports/mostUsedToppingPerProduct")).andDo(print())
        .andExpect(status().isOk());
  }

}
