package com.example.bank.Controllers;


import com.example.bank.Entities.Transactions;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Services.CustomerService;
import com.example.bank.Services.TransactionService;
import com.example.bank.Services.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {


    private final TransactionService transactionService;
    @GetMapping()
    public List<TransactionDto> GetUserTransactions (@Validated @RequestParam(name = "id")  long id){

        return  transactionService.GetTransactionOfAUser(id);

    }
}
