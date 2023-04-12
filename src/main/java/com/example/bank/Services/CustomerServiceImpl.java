package com.example.bank.Services;

import com.example.bank.Entities.Customer;
import com.example.bank.Entities.Transactions;
import com.example.bank.Exception.CustomerApiException;
import com.example.bank.HelperClasses.AccountMethods;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Mapper.TransactionMapper;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final TransactionRepository transactionRepository;
    private final CustomerMapper customerMapper;

    private final TransactionMapper transactionMapper;
    private final AccountMethods  accountMethods;

    @Override
    public CustomerDto createAccount(CustomerDto customerDto)  {

        Long AccountNumber=   this.accountMethods.createAccountNumber();

        Customer  ifCustomerExists= customerRepository
                .findByAccountName(AccountNumber.toString());


        if (ifCustomerExists !=null)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Name Already Exists");

        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);
        customer.setTransactions(customerDto.getTransactions());
        customer.setAccountNumber(AccountNumber);

       Customer newCustomer = customerRepository.save(customer);
        return customerMapper.CustomerToCustomerDto(newCustomer);
    }

    @Override
    public CustomerDto GetSingleUserAccountDetails(Long accountNumber) {

        Customer  ifCustomerExists= customerRepository
                .findByAccountNumber(accountNumber);



        return customerMapper.CustomerToCustomerDto(ifCustomerExists);
    }

    @Override
    public ResponseEntity MakeDeposit(Long accountNumber, TransactionDto dto) {

        System.out.println(accountNumber);
        System.out.println(dto);
        return this.accountMethods.makeDeposit(accountNumber,dto);


    }

    @Override
    public ResponseEntity MakeWithdrawal(Long accountNumber, TransactionDto dto) {

        return this.accountMethods.makeWithdrawal(accountNumber,dto);
    }
}
