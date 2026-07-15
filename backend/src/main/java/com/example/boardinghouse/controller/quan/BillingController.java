package com.example.boardinghouse.controller.quan;

import com.example.boardinghouse.model.quan.Bill;
import com.example.boardinghouse.repository.quan.BillRepository;
import com.example.boardinghouse.service.quan.BankTransferStrategy;
import com.example.boardinghouse.service.quan.EWalletStrategy;
import com.example.boardinghouse.service.quan.PaymentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BankTransferStrategy bankTransferStrategy;

    @Autowired
    private EWalletStrategy eWalletStrategy;

    @Autowired
    private PaymentContext paymentContext;

    @GetMapping
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        bill.setTotalAmount(bill.getElectricity() + bill.getWater() + bill.getRoomRent());
        bill.setStatus("Unpaid");
        return billRepository.save(bill);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payBill(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found"));
        if ("Paid".equals(bill.getStatus())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Bill is already paid."));
        }

        String method = payload.get("method");
        if ("Bank".equalsIgnoreCase(method)) {
            paymentContext.setStrategy(bankTransferStrategy);
        } else if ("E-Wallet".equalsIgnoreCase(method)) {
            paymentContext.setStrategy(eWalletStrategy);
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid payment method"));
        }

        boolean success = paymentContext.executePayment(bill.getTotalAmount());
        if (success) {
            bill.setStatus("Paid");
            billRepository.save(bill);
            return ResponseEntity.ok(Map.of("message", "Payment successful via " + method));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Payment failed"));
    }
}
