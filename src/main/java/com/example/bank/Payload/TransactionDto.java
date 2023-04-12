package com.example.bank.Payload;

import com.example.bank.Entities.Transactions;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;


@Data
public class TransactionDto {
    private long id;


    private Long currentBalance;


    private Long withdrawal;

    private Long deposit;

    private Long newBalance;




}