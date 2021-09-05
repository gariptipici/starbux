package com.bestseller.starbux.admin.service.impl;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.repository.CustomerInfoReportRepository;
import com.bestseller.starbux.admin.service.CustomerInfoReportService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoReportServiceImpl implements CustomerInfoReportService {
  private final CustomerInfoReportRepository customerInfoReportRepository;

  public CustomerInfoReportServiceImpl(
      CustomerInfoReportRepository customerInfoReportRepository) {
    this.customerInfoReportRepository = customerInfoReportRepository;
  }

  @Override
  public List<CustomerInfoDto> getTotalAmountPerCustomer() {
    List<Object[]> reports = customerInfoReportRepository.getTotalAmountPerCustomer();
    return reports.stream().map(objects -> {
      CustomerInfoDto dto = new CustomerInfoDto();
      dto.setCustomerId(((BigInteger)objects[0]).longValue());
      dto.setOrderTotalAmount((BigDecimal) objects[1]);
      return dto;
    }).collect(Collectors.toList());
  }
}
