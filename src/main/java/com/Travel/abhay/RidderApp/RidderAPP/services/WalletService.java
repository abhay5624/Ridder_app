package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Ride;
import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Wallet;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    Wallet addMoneyToWallet(UserEntity user, Double amount, String transectionId, Ride ride, TransactionMethod transactionMethod) ;

    Wallet addMoneyFromBank(Long userId,Double amount,String transectionId);

    void withDrawAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(UserEntity user);

    Wallet findByUser(UserEntity user);

    Wallet deductMoneyFromWallet(UserEntity user, Double amount, String transectionId, Ride ride, TransactionMethod transactionMethod) ;
}
