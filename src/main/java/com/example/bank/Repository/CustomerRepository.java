package com.example.bank.Repository;

import com.example.bank.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer,Long> {

   Optional<Customer> findByAccountName(String AccountName);

   Optional <Customer> findByAccountNumber(Long AccountNumber);
}

