package com.example.bank.services;

import com.example.bank.Entities.Customer;
import com.example.bank.Enums.AccountType;
import com.example.bank.HelperClasses.AccountMethods;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.NewCustomerDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import com.example.bank.Services.CustomerService;
import com.example.bank.Services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
                .email("mikel@gmail.com")
                .accountType(AccountType.current)
                .balance(0L)
                .accountName("Zobo Lord")
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

        NewCustomerDto newDto = new NewCustomerDto();
        newDto.setBalance(30000L);
        newDto.setEmail("mikel@gmail.com");
        newDto.setAge(23);
        newDto.setAccountType("current");
        newDto.setAccountName("Zobo Lord");
        newDto.setTransactions(new ArrayList<>());
        newDto.setAccountNumber(1_111_111_111L);

        Long AccNumber=   this.accountMethods.createAccountNumber();


        given(customerRepository
                .findByAccountNumber(AccNumber)).willReturn(Optional.empty());

        given(customerRepository
                .findByAccountName(customerDto.getAccountName())).willReturn(Optional.empty());

       when(customerMapper.CustomerDtoToCustomer(customerDto)).thenReturn(newCustomer);


        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);


        when(customerRepository
                .save(customer)).thenReturn(newCustomer);
        when(customerMapper.CustomerToNewCustomerDto(customer)).thenReturn(newDto);


        var createdCustomer = customerService.createAccount(customerDto);

        System.out.println(AccNumber);
        System.out.println(createdCustomer);

        assertNotNull(createdCustomer);


    }
}
