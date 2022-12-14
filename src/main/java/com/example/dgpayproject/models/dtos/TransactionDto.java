package com.example.dgpayproject.models.dtos;

import com.example.dgpayproject.base.BaseDto;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto extends BaseDto {
    private Long id;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("TransactionStatus")
    private TransactionStatus transactionStatus;
    @JsonProperty("description")
    private String description;
    @JsonProperty("walletName")
    private String walletName;
    private UserDto userDto;
}
