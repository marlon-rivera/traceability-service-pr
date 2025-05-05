package com.pragma.traceability_order_service.infrastructure.input.rest;

import com.pragma.traceability_order_service.application.dto.request.OrderLogCreateDto;
import com.pragma.traceability_order_service.application.handler.IOrderLogHandler;
import com.pragma.traceability_order_service.domain.model.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderLogControllerTest {

    @Mock
    private IOrderLogHandler orderLogHandler;

    @InjectMocks
    private OrderLogController orderLogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrderLog_ShouldReturnOkResponse() {
        // Arrange
        OrderLogCreateDto orderLogCreateDto = new OrderLogCreateDto();
        doNothing().when(orderLogHandler).saveOrderLog(orderLogCreateDto);

        // Act
        ResponseEntity<Void> response = orderLogController.saveOrderLog(orderLogCreateDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderLogHandler, times(1)).saveOrderLog(orderLogCreateDto);
    }

    @Test
    void getOrderLog_ShouldReturnOrderLogsList() {
        // Arrange
        Long orderId = 1L;
        List<OrderLog> expectedOrderLogs = Arrays.asList(
                new OrderLog(),
                new OrderLog()
        );
        when(orderLogHandler.getOrderLogsByOrderId(orderId)).thenReturn(expectedOrderLogs);

        // Act
        ResponseEntity<List<OrderLog>> response = orderLogController.getOrderLog(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrderLogs, response.getBody());
        verify(orderLogHandler, times(1)).getOrderLogsByOrderId(orderId);
    }

}