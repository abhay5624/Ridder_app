package com.Travel.abhay.RidderApp.RidderAPP.services.impl;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.TransactionMethod;
import com.Travel.abhay.RidderApp.RidderAPP.services.RazorPayService;
import com.Travel.abhay.RidderApp.RidderAPP.services.WalletService;
import com.razorpay.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RazorServiceImpl implements RazorPayService {
    @Value("${rzp_key_id}")
    private String keyId;

    @Value("${rzp_key_secret}")
    private String secret;

    private final WalletService walletService;
    @Override
    @Transactional
    public String transferMoney(int amount) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(keyId, secret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_receipt_11");

        Order order = razorpayClient.orders.create(orderRequest);
        String orderId = order.get("id");

        return orderId;
    }

    @Override
    @Transactional
    public String verifyPayment(Map<String, String> data, Long userId) throws RazorpayException, BadRequestException {
        String razorpayPaymentId = data.get("razorpay_payment_id");
        String razorpayOrderId = data.get("razorpay_order_id");
        String razorpaySignature = data.get("razorpay_signature");

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", razorpayOrderId);
        options.put("razorpay_payment_id", razorpayPaymentId);
        options.put("razorpay_signature", razorpaySignature);

        RazorpayClient razorpayClient = new RazorpayClient(keyId, secret);
        boolean isValidSignature = validatePaymentSignature(options, razorpaySignature);

        if (isValidSignature) {
            addVerifyMoneyToWallet(razorpayClient, razorpayPaymentId, userId);
            return "Payment is successful";
        } else {
            throw new BadRequestException("Invalid payment signature");
        }
    }

    private boolean validatePaymentSignature(JSONObject orderData, String razorpaySignature) throws RazorpayException {
        return Utils.verifyPaymentSignature(orderData, secret);
    }
    @Transactional
    private void addVerifyMoneyToWallet(RazorpayClient razorpayClient,String razorpayPaymentId,Long userId) throws RazorpayException {
        Payment payment = razorpayClient.payments.fetch(razorpayPaymentId);
        int amount = payment.get("amount");
        double amountInRupees = amount / 100.0;
        walletService.addMoneyFromBank(userId,amountInRupees,razorpayPaymentId);
    }
}
