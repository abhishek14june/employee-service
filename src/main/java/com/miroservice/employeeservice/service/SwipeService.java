package com.miroservice.employeeservice.service;

import com.miroservice.employeeservice.SwipeType;
import com.miroservice.employeeservice.controller.dto.EmployeeSwipeRequest;
import com.miroservice.employeeservice.entities.EmployeeSwipe;
import com.miroservice.employeeservice.repositories.EmployeeSwipeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SwipeService {
    private final EmployeeSwipeRepository swipeRepository;

    public SwipeService(EmployeeSwipeRepository swipeRepository) {
        this.swipeRepository = swipeRepository;
    }

    public void swipeIn(EmployeeSwipeRequest request) {
        EmployeeSwipe swipe = new EmployeeSwipe();
        swipe.setEmployeeId(request.getEmployeeId());
        swipe.setTimestamp(request.getTimestamp());
        swipe.setSwipeType(SwipeType.IN);

        swipeRepository.save(swipe);
    }

    public void swipeOut(EmployeeSwipeRequest request) {
        EmployeeSwipe swipe = new EmployeeSwipe();
        swipe.setEmployeeId(request.getEmployeeId());
        swipe.setTimestamp(request.getTimestamp());
        swipe.setSwipeType(SwipeType.OUT);

        swipeRepository.save(swipe);

    }
}
