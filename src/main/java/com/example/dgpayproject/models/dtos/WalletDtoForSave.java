package com.example.dgpayproject.models.dtos;

import com.example.dgpayproject.base.BaseDto;
import com.example.dgpayproject.models.enums.WalletStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WalletDtoForSave extends BaseDto {
    private Long id;
    @JsonProperty("walletName")
    private String name;
//    private Double balance;
//    @JsonProperty("walletStatus")
//    private WalletStatus walletStatus;

}
