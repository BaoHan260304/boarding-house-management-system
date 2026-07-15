package com.example.boardinghouse.service.quan;

import org.springframework.stereotype.Component;

@Component
public class EWalletStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Double amount) {
        System.out.println("Processing E-Wallet (Momo/ZaloPay) payment of $" + amount);
        // Integrate with real E-Wallet API here
        return true;
    }
}
