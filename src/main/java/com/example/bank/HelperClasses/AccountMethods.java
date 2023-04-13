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
import java.util.Random;



@Component

public class AccountMethods {
    private final static long min = 1_000_000_000L;
    private final static long max = 9_000_000_000L;



    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public long createAccountNumber(){

        long accountNumber = min + new Random().nextLong(max);
        System.out.println(accountNumber);
        return accountNumber;

    }


    public ResponseEntity makeDeposit(Long accountNumber, TransactionDto dto){

        if (dto.getDeposit() < 1  || dto.getDeposit() > 1_000_000)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Deposit must not be less than 1 naira or greater  than 1 million");


        Customer ifCustomerExists= customerRepository.findByAccountNumber(accountNumber);

        if (ifCustomerExists==null)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Number Does not Exists");


        Long newBalance = ifCustomerExists.getBalance() + dto.getDeposit();

        Transactions NewTransaction = new Transactions();
        NewTransaction.setCurrentBalance(ifCustomerExists.getBalance());
        NewTransaction.setDeposit(dto.getDeposit());
        NewTransaction.setNewBalance(newBalance);
        NewTransaction.setWithdrawal(dto.getWithdrawal());
        NewTransaction.setCustomer(ifCustomerExists);

        ifCustomerExists.setBalance(newBalance);

        customerRepository.save(ifCustomerExists);
        transactionRepository.save(NewTransaction);

        return new ResponseEntity<>("Deposit Made successfully", HttpStatus.OK);
    }

    public ResponseEntity makeWithdrawal(Long accountNumber, TransactionDto dto){

        Customer ifCustomerExists= customerRepository.findByAccountNumber(accountNumber);


        if (ifCustomerExists==null)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Number Does not Exists");


        System.out.println(ifCustomerExists.getBalance());
        System.out.println(dto.getWithdrawal());

        if (ifCustomerExists.getBalance() - dto.getWithdrawal() < 500)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Balance after withdrawal should be above 500naira");




        Long newBalance = ifCustomerExists.getBalance() - dto.getWithdrawal();

        Transactions NewTransaction = new Transactions();
        NewTransaction.setCurrentBalance(ifCustomerExists.getBalance());
        NewTransaction.setDeposit(dto.getDeposit());
        NewTransaction.setNewBalance(newBalance);
        NewTransaction.setWithdrawal(dto.getWithdrawal());
        NewTransaction.setCustomer(ifCustomerExists);

        ifCustomerExists.setBalance(newBalance);

        customerRepository.save(ifCustomerExists);
        transactionRepository.save(NewTransaction);

        return new ResponseEntity<>("Withdrawal Made successfully", HttpStatus.OK);
    }



}
