package com.miroservice.employeeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miroservice.employeeservice.SwipeType;
import com.miroservice.employeeservice.controller.dto.AttendanceCalculationResult;
import com.miroservice.employeeservice.entities.EmployeeSwipe;
import com.miroservice.employeeservice.repositories.EmployeeSwipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.miroservice.employeeservice.util.JsonConverter.convertToJson;

@Service
public class AttendanceCalculationService {
    Logger logger = LoggerFactory.getLogger(AttendanceCalculationService.class.getName());
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EmployeeSwipeRepository swipeRepository;

    @Autowired
    public AttendanceCalculationService(KafkaTemplate<String, String> kafkaTemplate,
                                        EmployeeSwipeRepository swipeRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.swipeRepository = swipeRepository;
    }

    public void calculateAttendanceForDate(Long employeeId, LocalDate date) {
        logger.info("compute hours for {}",employeeId);
        logger.info("compute for date {}",date);
        // Fetch employee swipes for the given date
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.atTime(LocalTime.MAX);
        List<EmployeeSwipe> swipes = swipeRepository.findByEmployeeIdAndTimestampBetween(employeeId, startDate, endDate);


        // Calculate total hours
        long totalHours = calculateTotalHours(swipes);
        logger.info("total hours {}",totalHours);
        String status = calculateStatus(totalHours);

        // Create an AttendanceCalculationResult
        AttendanceCalculationResult result = new AttendanceCalculationResult();
        result.setEmployeeId(employeeId);
        result.setDate(date);
        result.setHours(totalHours);
        result.setStatus(status);
        logger.info("attendance result {}",result);

        try {
            String serializedResult = convertToJson(result); // Convert to JSON
            logger.info("serializedResult {}",serializedResult);
            // Publish the result to the Kafka topic
            kafkaTemplate.send("employee-attendance-new", serializedResult);
            logger.info("data pushed to kafka");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private long calculateTotalHours(List<EmployeeSwipe> swipes) {
        if (swipes.isEmpty()) {
            return 0L; // No swipes, consider absent.
        }
        LocalDateTime firstIn = null;
        LocalDateTime lastOut = null;
        for (EmployeeSwipe swipe : swipes) {
            if (swipe.getSwipeType() == SwipeType.IN) {
                if (firstIn == null || swipe.getTimestamp().isBefore(firstIn)) {
                    firstIn = swipe.getTimestamp();
                }
            } else if (swipe.getSwipeType() == SwipeType.OUT) {
                if (lastOut == null || swipe.getTimestamp().isAfter(lastOut)) {
                    lastOut = swipe.getTimestamp();
                }
            }
        }
        if (firstIn != null && lastOut != null) {
            Duration duration = Duration.between(firstIn, lastOut);
            return duration.toHours(); // Return total hours in seconds.
        } else {
            return 0L; // Invalid data, consider absent.
        }
    }

    private String calculateStatus(long totalHours) {
        if (totalHours < 4) {
            return "Absent";
        } else if (totalHours >= 4 && totalHours < 8) {
            return "Half day";
        } else {
            return "Present";
        }
    }
}
