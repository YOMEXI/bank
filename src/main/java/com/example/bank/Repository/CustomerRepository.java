package com.example.bank.Repository;

import com.example.bank.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository <Customer,Long> {

    Customer findByAccountName(String AccountName);

    Customer findByAccountNumber(Long AccountNumber);
}

