package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Ride;
import com.Travel.abhay.RidderApp.RidderAPP.entities.UserEntity;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Wallet;
import com.Travel.abhay.RidderApp.RidderAPP.entities.WalletTransaction;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionType;
import com.Travel.abhay.RidderApp.RidderAPP.exceptions.ResourceNotFoundException;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.UserRepo;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.WalletRepo;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.WalletTransectionRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.UserService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletTransectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepo walletRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final WalletTransectionService walletTransectionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(UserEntity user, Double amount, String transectionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = walletRepo.findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransection  = WalletTransaction
                .builder()
                .transactionId(transectionId)
                .ride(ride)
                .amount(amount)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .wallet(wallet)
                .build();
        wallet.getWalletTransactions().add(walletTransection);
        walletTransectionService.createNewWalletTransaction(walletTransection);
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet addMoneyFromBank(Long userId, Double amount, String transectionId) {
        UserEntity user = userService.findUserById(userId);
        Wallet wallet = walletRepo.findByUser(user);
        double prevAmount = wallet.getBalance();
        wallet.setBalance(prevAmount+amount);
        walletRepo.save(wallet);
        WalletTransaction walletTransection  = WalletTransaction
                .builder()
                .transactionId(transectionId)
                .amount(amount)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(TransactionMethod.BANKING)
                .wallet(wallet)
                .build();
        walletTransectionService.createNewWalletTransaction(walletTransection);
        wallet.getWalletTransactions().add(walletTransection);
        return walletRepo.save(wallet);
    }

    @Override
    public void withDrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepo.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet with Id "+ walletId+" not found"));
    }

    @Override
    public Wallet createNewWallet(UserEntity user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet findByUser(UserEntity user) {
        return walletRepo.findByUser(user);
    }
    @Transactional
    @Override
    public Wallet deductMoneyFromWallet(UserEntity user, Double amount, String transectionId, Ride ride, TransactionMethod transactionMethod)  {
        Wallet wallet = walletRepo.findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTransaction walletTransection  = WalletTransaction
                .builder()
                .transactionId(transectionId)
                .ride(ride)
                .amount(amount)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .wallet(wallet)
                .build();

        wallet.getWalletTransactions().add(walletTransection);

        walletTransectionService.createNewWalletTransaction(walletTransection);
        return walletRepo.save(wallet);
    }
}
