package com.example.boardinghouse.repository.quan;

import com.example.boardinghouse.model.quan.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
