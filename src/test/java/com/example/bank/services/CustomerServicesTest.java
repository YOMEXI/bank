package com.example.bank.services;

import com.example.bank.HelperClasses.AccountMethods;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import com.example.bank.Services.CustomerService;
import com.example.bank.Services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerServicesTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    private  TransactionRepository transactionRepository;
    private CustomerMapper customerMapper;
    private AccountMethods accountMethods;

    @BeforeEach
    public void setup(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository
                ,transactionRepository,customerMapper,accountMethods);

    }

    @Test()
    public  void givenCustomerDto_whenSave_thenReturnCreatedAccount(){


    }
}
