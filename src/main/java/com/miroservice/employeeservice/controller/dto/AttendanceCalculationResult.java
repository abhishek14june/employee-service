package com.miroservice.employeeservice.controller.dto;

import java.time.LocalDate;

public class AttendanceCalculationResult {
    private Long employeeId;
    private LocalDate date;
    private String status; // Absent, Half day, Present

    private Long hours;

    // Constructors, getters, and setters

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "AttendanceCalculationResult{" +
                "employeeId=" + employeeId +
                ", date=" + date +
                ", hours=" + hours +
                ", status='" + status + '\'' +
                '}';
    }
}
