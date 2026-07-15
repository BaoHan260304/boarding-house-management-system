package com.example.boardinghouse.controller.quan;

import com.example.boardinghouse.model.quan.MaintenanceRequest;
import com.example.boardinghouse.repository.quan.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "*")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @GetMapping
    public List<MaintenanceRequest> getAllRequests() {
        return maintenanceRepository.findAll();
    }

    @PostMapping
    public MaintenanceRequest createRequest(@RequestBody MaintenanceRequest request) {
        request.setStatus("Pending");
        return maintenanceRepository.save(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        MaintenanceRequest request = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        String status = payload.get("status");
        if (status != null) {
            request.setStatus(status);
            maintenanceRepository.save(request);
            return ResponseEntity.ok(Map.of("message", "Maintenance status updated to " + status));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Status is required"));
    }
}
