package com.bestseller.starbux.shop.mapper;

import com.bestseller.starbux.shop.dto.CustomerDto;
import com.bestseller.starbux.shop.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CustomerMapper {
    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public abstract Customer customerDtoToCustomer(CustomerDto customerDto);
    public abstract CustomerDto customerToCustomerDto(Customer customer);
}
