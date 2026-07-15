package com.example.boardinghouse.model.quan;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;
    
    @Column(name = "billing_month")
    private String month;
    private Double electricity;
    private Double water;
    private Double roomRent;
    private Double totalAmount;
    private String status; // Unpaid, Paid, Overdue

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    public Double getElectricity() { return electricity; }
    public void setElectricity(Double electricity) { this.electricity = electricity; }
    public Double getWater() { return water; }
    public void setWater(Double water) { this.water = water; }
    public Double getRoomRent() { return roomRent; }
    public void setRoomRent(Double roomRent) { this.roomRent = roomRent; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
