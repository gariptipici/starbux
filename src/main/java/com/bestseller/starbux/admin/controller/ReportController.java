package com.bestseller.starbux.admin.controller;

import com.bestseller.starbux.common.dto.ProductDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/reports")
public class ReportController {

  @GetMapping("/orderCountByCustomer")
  public List<ProductDto> readProducts(){
    return null;
  }

  @GetMapping("/mostUsedToppingsForDrinks")
  public List<ProductDto> readProductss(){
    return null;
  }

}
