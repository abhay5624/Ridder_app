package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.entities.WalletTransaction;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.WalletTransectionRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletTransectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WalletTransectionServiceImple implements WalletTransectionService {

    private final WalletTransectionRepo walletTransectionRepo;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction  walletTransection ) {
        walletTransectionRepo.save(walletTransection);
    }
}
