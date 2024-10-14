package com.Travel.abhay.RidderApp.RidderAPP.dto;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTransectionDto {
    private Long Id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private RideDto ride;

    private WalletDto wallet;


    private String transactionId;
}
