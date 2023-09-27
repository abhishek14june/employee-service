package com.miroservice.employeeservice.controller;

import com.miroservice.employeeservice.controller.dto.AttendanceCalculationResult;
import com.miroservice.employeeservice.service.AttendanceCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class AttendanceCalculationController {
    private final AttendanceCalculationService attendanceCalculationService;
    private final KafkaTemplate<String, AttendanceCalculationResult> kafkaTemplate;

    public AttendanceCalculationController(
            AttendanceCalculationService attendanceCalculationService, KafkaTemplate<String, AttendanceCalculationResult> kafkaTemplate) {
        this.attendanceCalculationService = attendanceCalculationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/hours")
    public ResponseEntity<String> calculateHours(@RequestParam Long employeeId, @RequestParam LocalDate date) {
        // Fetch swipe data for the specified employee and date
        attendanceCalculationService.calculateAttendanceForDate(employeeId,date);

        return ResponseEntity.ok("Attendance calculation completed.");
    }

}
