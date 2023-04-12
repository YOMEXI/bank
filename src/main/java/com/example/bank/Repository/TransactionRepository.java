package com.example.bank.Repository;

import com.example.bank.Entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query(value = "SELECT * FROM transactions where customer_id = ?1", nativeQuery = true)
   List   <Transactions> getTransactionsDoneByAUser(Long id);
}
