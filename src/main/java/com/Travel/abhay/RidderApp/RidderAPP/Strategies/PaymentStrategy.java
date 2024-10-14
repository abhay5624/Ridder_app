package com.Travel.abhay.RidderApp.RidderAPP.Strategies;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMISSION = 0.3;
    void processPayment(Payment payment);
}
