package com.example.bank.Controllers;

import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.NewCustomerDto;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping()
    public NewCustomerDto CreateCustomer (@Validated @RequestBody CustomerDto customer){

        return  customerService.createAccount(customer);

    }


    @PostMapping("/deposit")
    public ResponseEntity<String> MakeDeposit (@Validated @RequestBody TransactionDto transaction, @RequestParam(name = "id")  long id){

       return customerService.MakeDeposit(id, transaction);


    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> MakeWithdrawal (@Validated @RequestBody TransactionDto transaction, @RequestParam(name = "id")  long id){

        return customerService.MakeWithdrawal(id, transaction);


    }

    @GetMapping("/account")
    public CustomerDto GetUserAccountDetails (@RequestParam(name = "accountno")  long accountno){

       return customerService.GetSingleUserAccountDetails(accountno);

    }
}
