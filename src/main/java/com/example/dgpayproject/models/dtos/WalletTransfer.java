package com.example.dgpayproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransfer {
        @JsonProperty("amount")
        private Double amount;
        @JsonProperty("receiverUsername")
        private String receiverUsername;
        @JsonProperty("sanderWallet")
        private String sourceWallet;
        @JsonProperty("receiverWallet")
        private String targetWallet;
}
