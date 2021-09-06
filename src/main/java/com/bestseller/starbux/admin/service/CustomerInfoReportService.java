package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.dto.ProductInfoDto;
import java.util.List;

public interface CustomerInfoReportService {
  List<CustomerInfoDto> getTotalAmountPerCustomer();
  List<ProductInfoDto> getMostUsedToppingPerProduct();

}
