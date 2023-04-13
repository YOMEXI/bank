package com.example.bank.services;

import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Services.CustomerService;
import com.example.bank.Services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class CustomerServicesTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    public void setup(){
        customerRepository = Mockito.mock(CustomerRepository.class);

    }
}
