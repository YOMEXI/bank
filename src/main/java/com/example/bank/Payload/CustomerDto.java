package com.example.bank.Payload;


import com.example.bank.Entities.Transactions;
import com.example.bank.Enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Set;

@Data
public class CustomerDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "accountName should have at least 2 characters")
    private String accountName;

    private String accountType;

    @NotNull
    @Min(value = 500,message = "Balance must be a minimum of 500")
    private Long balance;


    @Email(regexp = ".+[@].+[\\.].+",message = "Please Include a valid email")
    private String email;

    @NotNull
    @Min(18)
    private Integer age;

    private List<Transactions> transactions;


}