package com.miroservice.employeeservice.repositories;

import com.miroservice.employeeservice.entities.EmployeeSwipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeSwipeRepository extends JpaRepository<EmployeeSwipe, Long> {
    List<EmployeeSwipe> findByEmployeeIdAndTimestampBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
}
