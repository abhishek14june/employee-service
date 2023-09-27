package com.miroservice.employeeservice.controller.dto;

import java.time.LocalDateTime;

public class EmployeeSwipeRequest {
    private Long employeeId;
    private LocalDateTime timestamp;


    // Constructors, getters, and setters

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EmployeeSwipeRequest{" +
                "employeeId=" + employeeId +
                ", timestamp=" + timestamp +
                '}';
    }
}
