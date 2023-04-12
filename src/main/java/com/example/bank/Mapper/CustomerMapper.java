package com.example.bank.Mapper;

import com.example.bank.Entities.Customer;
import com.example.bank.Payload.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer CustomerDtoToCustomer (CustomerDto dto);

    CustomerDto CustomerToCustomerDto (Customer customer);
}