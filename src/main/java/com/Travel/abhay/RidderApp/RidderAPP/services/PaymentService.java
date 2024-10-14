package com.Travel.abhay.RidderApp.RidderAPP.services;

import com.Travel.abhay.RidderApp.RidderAPP.entities.Payment;
import com.Travel.abhay.RidderApp.RidderAPP.entities.Ride;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void processPayment(Payment payment);

    Payment createNewPayment(Ride ride);

    Payment updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
