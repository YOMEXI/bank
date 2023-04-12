package com.example.bank.Services;

import com.example.bank.Entities.Transactions;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.TransactionDto;

import java.util.List;

public interface TransactionService {

  List <TransactionDto> GetTransactionOfAUser(Long id);
}
