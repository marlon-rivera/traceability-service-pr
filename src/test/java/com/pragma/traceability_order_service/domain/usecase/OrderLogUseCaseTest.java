package com.pragma.traceability_order_service.domain.usecase;

import com.pragma.traceability_order_service.domain.exception.InvalidDataException;
import com.pragma.traceability_order_service.domain.exception.ResourceNotFoundException;
import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.domain.spi.IAutthenticatePort;
import com.pragma.traceability_order_service.domain.spi.IOrderLogPersistencePort;
import com.pragma.traceability_order_service.domain.util.constants.OrderLogUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderLogUseCaseTest {

    @Mock
    private IOrderLogPersistencePort orderLogPersistencePort;

    @Mock
    private IAutthenticatePort autthenticatePort;

    private OrderLogUseCase orderLogUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderLogUseCase = new OrderLogUseCase(orderLogPersistencePort, autthenticatePort);
    }

    @Test
    void saveOrderLog_shouldSaveLogSuccessfully() {
        // Arrange
        Long idOrder = 1L;
        Long idClient = 2L;
        Long idRestaurant = 3L;
        Long idEmployee = 4L;
        String status = "PENDIENTE";

        // Act
        orderLogUseCase.saveOrderLog(idOrder, idClient, idRestaurant, idEmployee, status);

        // Assert
        verify(orderLogPersistencePort, times(1)).saveOrderLog(any(OrderLog.class));
    }

    @Test
    void getOrderLogsByIdOrder_shouldReturnOrderLogs_whenOrderExists() {
        // Arrange
        Long idOrder = 1L;
        Long idClient = 2L;
        Long idRestaurant = 3L;
        Long idEmployee = 4L;
        String status = "PENDIENTE";

        OrderLog orderLog = new OrderLog("abcd", idOrder, idClient, idRestaurant, idEmployee, status, LocalDateTime.now());
        List<OrderLog> orderLogs = Collections.singletonList(orderLog);

        when(orderLogPersistencePort.getOrderLogsByOrderId(idOrder)).thenReturn(orderLogs);
        when(autthenticatePort.getCurrentUserId()).thenReturn(idClient);

        // Act
        List<OrderLog> result = orderLogUseCase.getOrderLogsByIdOrder(idOrder);

        // Assert
        assertEquals(orderLogs, result);
        verify(orderLogPersistencePort, times(2)).getOrderLogsByOrderId(idOrder);
        verify(autthenticatePort, times(1)).getCurrentUserId();
    }

    @Test
    void getOrderLogsByIdOrder_shouldThrowResourceNotFoundException_whenOrderDoesNotExist() {
        // Arrange
        Long idOrder = 1L;
        when(orderLogPersistencePort.getOrderLogsByOrderId(idOrder)).thenReturn(Collections.emptyList());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> orderLogUseCase.getOrderLogsByIdOrder(idOrder));

        assertEquals(OrderLogUseCaseConstants.ORDER_NOT_FOUND, exception.getMessage());
        verify(orderLogPersistencePort, times(1)).getOrderLogsByOrderId(idOrder);
        verify(autthenticatePort, never()).getCurrentUserId();
    }

    @Test
    void getOrderLogsByIdOrder_shouldThrowInvalidDataException_whenOrderDoesNotBelongToClient() {
        // Arrange
        Long idOrder = 1L;
        Long idClient = 2L;
        Long differentClient = 3L;
        Long idRestaurant = 4L;
        Long idEmployee = 5L;
        String status = "PENDIENTE";

        OrderLog orderLog = new OrderLog("abcd", idOrder, idClient, idRestaurant, idEmployee, status, LocalDateTime.now());
        List<OrderLog> orderLogs = Collections.singletonList(orderLog);

        when(orderLogPersistencePort.getOrderLogsByOrderId(idOrder)).thenReturn(orderLogs);
        when(autthenticatePort.getCurrentUserId()).thenReturn(differentClient);

        // Act & Assert
        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> orderLogUseCase.getOrderLogsByIdOrder(idOrder));

        assertEquals(OrderLogUseCaseConstants.ORDER_NOT_BELONG_TO_CLIENT, exception.getMessage());
        verify(orderLogPersistencePort, times(1)).getOrderLogsByOrderId(idOrder);
        verify(autthenticatePort, times(1)).getCurrentUserId();
    }

}