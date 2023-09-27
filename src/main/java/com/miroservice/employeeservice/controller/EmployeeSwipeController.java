package com.miroservice.employeeservice.controller;

import com.miroservice.employeeservice.SwipeType;
import com.miroservice.employeeservice.controller.dto.EmployeeSwipeRequest;
import com.miroservice.employeeservice.entities.EmployeeSwipe;
import com.miroservice.employeeservice.repositories.EmployeeSwipeRepository;
import com.miroservice.employeeservice.service.SwipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/swipe")
public class EmployeeSwipeController {
    private final SwipeService swipeService;

    public EmployeeSwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @PostMapping("/in")
    public ResponseEntity<String> recordSwipeIn(@RequestBody EmployeeSwipeRequest request) {

        swipeService.swipeIn(request);
        return ResponseEntity.ok("Swipe in recorded successfully.");
    }

    @PostMapping("/out")
    public ResponseEntity<String> recordSwipeOut(@RequestBody EmployeeSwipeRequest request) {
        swipeService.swipeOut(request);
        return ResponseEntity.ok("Swipe out recorded successfully.");
    }
}
