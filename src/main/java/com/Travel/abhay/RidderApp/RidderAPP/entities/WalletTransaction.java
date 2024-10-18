package com.Travel.abhay.RidderApp.RidderAPP.entities;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
