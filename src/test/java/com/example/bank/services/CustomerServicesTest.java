package com.example.bank.services;

import com.example.bank.Entities.Customer;
import com.example.bank.Enums.AccountType;
import com.example.bank.HelperClasses.AccountMethods;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import com.example.bank.Services.CustomerService;
import com.example.bank.Services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServicesTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private  TransactionRepository transactionRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private AccountMethods accountMethods;

    private Customer newCustomer;

    private CustomerDto customerDto;

    @BeforeEach
    public void setup(){
        List transactions = new ArrayList<>();
         newCustomer = Customer.builder()
                .age(23)
                .transactions(transactions)
                .email("mikel@gmail.com")
                .accountType(AccountType.current)
                .balance(0L)
                .accountName("Zobo Lord")
                .accountNumber(1111111111L)
                .age(23)
                .build();

    }

    @Test()
    public  void givenCustomerDto_whenSave_thenReturnCreatedAccount(){

        CustomerDto customerDto = new CustomerDto();
        customerDto.setBalance(30000L);
        customerDto.setEmail("mikel@gmail.com");
        customerDto.setAge(23);
        customerDto.setAccountType("current");
        customerDto.setAccountName("Zobo Lord");
        customerDto.setTransactions(new ArrayList<>());


        Long AccountNumber=   0L;




        given(customerRepository
                .findByAccountNumber(AccountNumber)).willReturn(Optional.empty());

        given(customerRepository
                .findByAccountName(customerDto.getAccountName())).willReturn(Optional.empty());

       when(customerMapper.CustomerDtoToCustomer(customerDto)).thenReturn(newCustomer);


        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);

        when(customerMapper.CustomerToCustomerDto(customer)).thenReturn(customerDto);
        when(customerRepository
                .save(customer)).thenReturn(newCustomer);



        var createdCustomer = customerService.createAccount(customerDto);

        System.out.println(createdCustomer);
        assertThat(createdCustomer.getAccountName()).isEqualTo("Zobo Lord");




        assertThat(createdCustomer).isNotNull();


    }
}
