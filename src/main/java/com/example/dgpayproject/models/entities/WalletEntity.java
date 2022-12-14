package com.example.dgpayproject.models.entities;

import com.example.dgpayproject.base.BaseEntity;
import com.example.dgpayproject.models.enums.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLET",uniqueConstraints = { @UniqueConstraint(columnNames =
        { "name","user_id" }) })
public class WalletEntity extends BaseEntity {


    public WalletEntity( Long id, String name, Double balance, WalletStatus walletStatus) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.walletStatus = walletStatus;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "total_balance")
    private Double balance;
    @Column(name = "wallet_status")
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "walletEntity")
    private List<TransactionEntity> transactions;
}
