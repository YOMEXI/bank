package com.example.bank.HelperClasses;

import com.example.bank.Entities.Customer;
import com.example.bank.Entities.Transactions;
import com.example.bank.Exception.CustomerApiException;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;



@Component

public class AccountMethods {
    private final static long min = 1_000_000_000L;
    private final static long max = 9_000_000_000L;



    public long createAccountNumber(){

        long accountNumber = min + new Random().nextLong(max);
        System.out.println(accountNumber);
        return accountNumber;

    }






}
