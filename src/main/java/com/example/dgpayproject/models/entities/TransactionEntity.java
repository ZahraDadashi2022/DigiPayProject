package com.example.dgpayproject.models.entities;

import com.example.dgpayproject.base.BaseEntity;
import com.example.dgpayproject.models.enums.TransactionStatus;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity extends BaseEntity {
    public TransactionEntity(Long id, String accountNumber, Double amount,
                             TransactionStatus transactionStatus, String description) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletEntity walletEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
