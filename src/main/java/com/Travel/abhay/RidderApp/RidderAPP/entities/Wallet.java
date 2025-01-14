package com.Travel.abhay.RidderApp.RidderAPP.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private UserEntity user;

    private Double balance = 0.0;

    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
    private List<WalletTransaction> walletTransactions;
}
