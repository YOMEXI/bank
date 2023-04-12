package com.example.bank.Mapper;

import com.example.bank.Entities.Customer;
import com.example.bank.Entities.Transactions;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.TransactionDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transactions TransactionsDtoTransaction (TransactionDto dto);

    TransactionDto TransactionToTransactionDto (Transactions transactions);

   List<TransactionDto> TransactionToTransactionDtoList (List <Transactions> transactions);
}