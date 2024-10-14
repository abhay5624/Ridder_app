package com.Travel.abhay.RidderApp.RidderAPP.Strategies;

import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.CashPaymentStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.Strategies.Imple.WalletPaymentStrategy;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case PaymentMethod.WALLET -> walletPaymentStrategy;
            case PaymentMethod.CASH -> cashPaymentStrategy;
        };
    }

}
