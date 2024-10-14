package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.entities.WalletTransaction;
import org.springframework.stereotype.Service;

@Service
public interface WalletTransectionService {

    void createNewWalletTransaction(WalletTransaction walletTransection);

}
