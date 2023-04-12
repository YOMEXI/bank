package com.example.bank.Services;

import com.example.bank.Entities.Transactions;
import com.example.bank.Exception.CustomerApiException;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Mapper.TransactionMapper;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    @Override
    public List<TransactionDto> GetTransactionOfAUser(Long id) {

        var AllUserTransaction = transactionRepository.getTransactionsDoneByAUser(id);
        System.out.println(AllUserTransaction);
        System.out.println(id);

        if (AllUserTransaction.isEmpty())
         throw new CustomerApiException(HttpStatus.NO_CONTENT,"User has No Transactions Yet");


        return transactionMapper.TransactionToTransactionDtoList(AllUserTransaction);
    }
}


