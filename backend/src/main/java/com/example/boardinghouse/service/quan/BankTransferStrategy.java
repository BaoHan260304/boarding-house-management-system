package com.example.boardinghouse.service.quan;

import org.springframework.stereotype.Component;

@Component
public class BankTransferStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Double amount) {
        System.out.println("Processing Bank Transfer payment of $" + amount);
        // Integrate with real bank API here
        return true;
    }
}
