package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.dto.ProductInfoDto;
import com.bestseller.starbux.admin.service.CustomerInfoReportService;
import com.bestseller.starbux.common.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/reports")
@Api("Set of endpoints for reports.")
public class ReportController {

  private final CustomerInfoReportService customerInfoReportService;

  public ReportController(CustomerInfoReportService customerInfoReportService) {
    this.customerInfoReportService = customerInfoReportService;
  }

  @GetMapping("/orderCountByCustomer")
  @ApiOperation("Returns the information of each customer.")
  @ResponseStatus(HttpStatus.OK)
  public List<CustomerInfoDto> getTotalAmountPerCustomer(){

    return customerInfoReportService.getTotalAmountPerCustomer();
  }

  @GetMapping("/mostUsedToppingPerProduct")
  @ApiOperation("Returns the information of mostUsedToppingPerProduct.")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductInfoDto> getMostUsedToppingPerProduct(){

    return customerInfoReportService.getMostUsedToppingPerProduct();
  }


}
