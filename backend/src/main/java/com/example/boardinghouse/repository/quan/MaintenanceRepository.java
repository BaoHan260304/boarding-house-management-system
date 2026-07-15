package com.example.boardinghouse.repository.quan;

import com.example.boardinghouse.model.quan.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceRequest, Long> {
}
