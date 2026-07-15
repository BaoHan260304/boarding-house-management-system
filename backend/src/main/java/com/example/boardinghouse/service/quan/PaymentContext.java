package com.example.boardinghouse.service.quan;

import org.springframework.stereotype.Service;

@Service
public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executePayment(Double amount) {
        if (this.strategy == null) {
            throw new IllegalStateException("Payment strategy not set!");
        }
        return this.strategy.pay(amount);
    }
}
