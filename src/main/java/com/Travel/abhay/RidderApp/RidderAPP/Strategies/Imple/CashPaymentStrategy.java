package com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.PaymentStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Driver;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Payment;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentStatus;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.services.PaymentService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentService paymentService;
    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platFormComission = payment.getAmount()*PLATFORM_COMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(),platFormComission ,null,payment.getRide(), TransactionMethod.RIDE);
        paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
    }
}
