package com.bestseller.starbux.admin.service;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import java.util.List;

public interface CustomerInfoReportService {
  List<CustomerInfoDto> getTotalAmountPerCustomer();

}
