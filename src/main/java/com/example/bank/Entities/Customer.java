package com.example.bank.Entities;
import com.example.bank.Enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "customer"
)
public class Customer  implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "accountName", nullable = false)
    private String accountName;


    @Column(name = "email", nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "accountType")
    private AccountType accountType;

    @Column(name = "age", nullable = false)
    private Integer  age;

    @Column(name = "balance", nullable = false)
    private Long balance ;

    @Column(name = "accountNumber", nullable = false,length = 10,unique = true)
    private Long  accountNumber;


    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactions ;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
