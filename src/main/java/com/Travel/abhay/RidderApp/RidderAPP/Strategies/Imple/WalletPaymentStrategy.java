package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.PaymentStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Payment;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ridder;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentStatus;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.repositories.PaymentRepo;
import com.Travel.abhay.RidderApp.RidderAPP.services.PaymentService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepo paymentRepo;
    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Ridder ridder = payment.getRide().getRidder();

        walletService.deductMoneyFromWallet(ridder.getUser(),payment.getAmount(),null,payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getAmount()*(1 - PLATFORM_COMISSION);

        walletService.addMoneyToWallet(driver.getUser(),driverCut,null,payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepo.save(payment);

    }
}
