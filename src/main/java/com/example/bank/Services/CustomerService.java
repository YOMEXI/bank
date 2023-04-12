package com.example.bank.Services;

import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    CustomerDto createAccount(CustomerDto customerDto);

    CustomerDto GetSingleUserAccountDetails(Long accountNumber);

    ResponseEntity MakeDeposit(Long accountNumber, TransactionDto dto);


    ResponseEntity MakeWithdrawal(Long accountNumber, TransactionDto dto);
}
