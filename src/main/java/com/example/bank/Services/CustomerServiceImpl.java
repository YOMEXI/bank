package com.example.bank.Services;

import com.example.bank.Entities.Customer;
import com.example.bank.Entities.Transactions;
import com.example.bank.Exception.CustomerApiException;
import com.example.bank.HelperClasses.AccountMethods;
import com.example.bank.Mapper.CustomerMapper;
import com.example.bank.Mapper.TransactionMapper;
import com.example.bank.Payload.CustomerDto;
import com.example.bank.Payload.NewCustomerDto;
import com.example.bank.Payload.TransactionDto;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final TransactionRepository transactionRepository;
    private final CustomerMapper customerMapper;
    private final AccountMethods  accountMethods;

    @Override
    public NewCustomerDto createAccount(CustomerDto customerDto)  {

        if(customerDto.getBalance() < 500){
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,
                    "Minimum initial deposit should be 500");
        }

        Long AccountNumber=   this.accountMethods.createAccountNumber();

       Optional <Customer>  ifAccountNumberHasAlreadyBeenAssigned= customerRepository
                .findByAccountNumber(AccountNumber);

        if (ifAccountNumberHasAlreadyBeenAssigned.isPresent())
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,
                    "Account Number Already Assigned please try again");

       Optional <Customer>  ifCustomerExists= customerRepository
                .findByAccountName(customerDto.getAccountName());


        if (ifCustomerExists.isPresent())
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Name Already Exists");

        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);
        customer.setTransactions(customerDto.getTransactions());
        customer.setAccountNumber(AccountNumber);

       Customer newCustomer = customerRepository.save(customer);
        return customerMapper.CustomerToNewCustomerDto(newCustomer);
    }

    @Override
    public CustomerDto GetSingleUserAccountDetails(Long accountNumber) {

       Optional<Customer>  ifCustomerExists= customerRepository
                .findByAccountNumber(accountNumber);



        return customerMapper.CustomerToCustomerDto(ifCustomerExists.get());
    }

    @Override
    public ResponseEntity MakeDeposit(Long accountNumber, TransactionDto dto) {


        if (dto.getDeposit() < 1  || dto.getDeposit() > 1_000_000)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Deposit must not be less than 1 naira or greater  than 1 million");


        Optional<Customer> ifCustomerExists= customerRepository.findByAccountNumber(accountNumber);

        if (ifCustomerExists.isEmpty())
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Number Does not Exists");


        Long newBalance = ifCustomerExists.get().getBalance() + dto.getDeposit();

        Transactions NewTransaction = new Transactions();
        NewTransaction.setCurrentBalance(ifCustomerExists.get().getBalance());
        NewTransaction.setDeposit(dto.getDeposit());
        NewTransaction.setNewBalance(newBalance);
        NewTransaction.setWithdrawal(dto.getWithdrawal());
        NewTransaction.setCustomer(ifCustomerExists.get());

        ifCustomerExists.get().setBalance(newBalance);

        customerRepository.save(ifCustomerExists.get());
        transactionRepository.save(NewTransaction);

        return new ResponseEntity<>("Deposit Made successfully", HttpStatus.OK);

    }

    @Override
    public ResponseEntity MakeWithdrawal(Long accountNumber, TransactionDto dto) {

        if (dto.getWithdrawal() <  1)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Withdrawal must not be less than 1 naira or greater  than 1 million");


        Optional <Customer> ifCustomerExists= customerRepository.findByAccountNumber(accountNumber);

        if (ifCustomerExists.isEmpty())
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Account with Account Number Does not Exists");


        if (ifCustomerExists.get().getBalance() - dto.getWithdrawal() < 500)
            throw new CustomerApiException(HttpStatus.BAD_REQUEST,"Balance after withdrawal should be above 500naira");


        Long newBalance = ifCustomerExists.get().getBalance() - dto.getWithdrawal();

        Transactions NewTransaction = new Transactions();
        NewTransaction.setCurrentBalance(ifCustomerExists.get().getBalance());
        NewTransaction.setDeposit(dto.getDeposit());
        NewTransaction.setNewBalance(newBalance);
        NewTransaction.setWithdrawal(dto.getWithdrawal());
        NewTransaction.setCustomer(ifCustomerExists.get());

        ifCustomerExists.get().setBalance(newBalance);

        customerRepository.save(ifCustomerExists.get());
        transactionRepository.save(NewTransaction);

        return new ResponseEntity<>("Withdrawal Made successfully", HttpStatus.OK);
    }
}
