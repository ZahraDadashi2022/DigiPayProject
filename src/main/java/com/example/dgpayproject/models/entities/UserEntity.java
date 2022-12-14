package com.example.dgpayproject.models.entities;

import com.example.dgpayproject.base.BaseEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity {
    public UserEntity(Long id, String fname, String lname, String username, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;
    @NotBlank(message = "Username is mandatory")
    @NotEmpty
    @Size(min = 2, max = 20)
    @Column(name = "username")
    private String username;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @OneToMany
    private List<WalletEntity> wallets;
    @OneToMany(mappedBy = "userEntity")
    private List<TransactionEntity>transactions;
}
