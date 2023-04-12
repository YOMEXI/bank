package com.example.bank.Repository;


import com.example.bank.Entities.Customer;
import com.example.bank.Enums.AccountType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class CustomerRepositoryTests {
    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("Junit Test for creating customer")
    @Test
    public  void givenCustomerDto_whenSave_thenReturnCreatedAccount(){

        List transactions = new ArrayList<>();
        Customer customer = Customer.builder()
                .age(23)
                .transactions(transactions)
                .email("titus@gmail.com")
                .accountType(AccountType.current)
                .balance(50000L)
                .accountName("Kid flash")
                .accountNumber(1111111111L)
                .build();

        Customer createdAccount = customerRepository.save(customer);

        Assertions.assertThat(createdAccount).isNotNull();
        Assertions.assertThat(createdAccount.getId()).isGreaterThan(0);


    }
}
