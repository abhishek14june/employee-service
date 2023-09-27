package com.miroservice.employeeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miroservice.employeeservice.controller.EmployeeSwipeController;
import com.miroservice.employeeservice.controller.dto.EmployeeSwipeRequest;
import com.miroservice.employeeservice.service.SwipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class EmployeeSwipeControllerTest {

    @Mock
    private SwipeService swipeService;

    @InjectMocks
    private EmployeeSwipeController controller;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRecordSwipeIn() throws Exception {
        EmployeeSwipeRequest request = new EmployeeSwipeRequest();
        request.setEmployeeId(1234L);
        request.setTimestamp(LocalDateTime.now());

        // Mock the service method to do nothing for simplicity
        doNothing().when(swipeService).swipeIn(request);

        ResponseEntity<String> response = controller.recordSwipeIn(request);

        // Verify that the service method was called once
        verify(swipeService, times(1)).swipeIn(request);
    }

    @Test
    public void testRecordSwipeOut() throws Exception {
        EmployeeSwipeRequest request = new EmployeeSwipeRequest();
        request.setEmployeeId(1234L);
        request.setTimestamp(LocalDateTime.now());

        // Mock the service method to do nothing for simplicity
        doNothing().when(swipeService).swipeOut(request);

        ResponseEntity<String> response = controller.recordSwipeOut(request);

        // Verify that the service method was called once
        verify(swipeService, times(1)).swipeOut(request);
    }
}
