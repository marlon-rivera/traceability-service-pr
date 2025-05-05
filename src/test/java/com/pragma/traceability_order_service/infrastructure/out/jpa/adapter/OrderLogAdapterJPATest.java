package com.pragma.traceability_order_service.infrastructure.out.jpa.adapter;

import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.infrastructure.out.jpa.entity.OrderLogDocument;
import com.pragma.traceability_order_service.infrastructure.out.jpa.mapper.IOrderLogDocumentMapper;
import com.pragma.traceability_order_service.infrastructure.out.jpa.repository.IOrderLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderLogAdapterJPATest {

    @Mock
    private IOrderLogRepository orderLogRepository;

    @Mock
    private IOrderLogDocumentMapper orderLogDocumentMapper;

    private OrderLogAdapterJPA orderLogAdapterJPA;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderLogAdapterJPA = new OrderLogAdapterJPA(orderLogRepository, orderLogDocumentMapper);
    }

    @Test
    void saveOrderLog_shouldSaveOrderLogSuccessfully() {
        // Arrange
        OrderLog orderLog = new OrderLog("1", 1L, 2L, 3L, 4L, "PENDING", LocalDateTime.now());
        OrderLogDocument orderLogDocument = new OrderLogDocument();
        when(orderLogDocumentMapper.toOrderLogDocument(orderLog)).thenReturn(orderLogDocument);

        // Act
        orderLogAdapterJPA.saveOrderLog(orderLog);

        // Assert
        verify(orderLogDocumentMapper, times(1)).toOrderLogDocument(orderLog);
        verify(orderLogRepository, times(1)).save(orderLogDocument);
    }

    @Test
    void getOrderLogsByOrderId_shouldReturnOrderLogsList() {
        // Arrange
        Long orderId = 1L;
        List<OrderLogDocument> orderLogDocuments = new ArrayList<>();
        orderLogDocuments.add(new OrderLogDocument());

        List<OrderLog> expectedOrderLogs = new ArrayList<>();
        expectedOrderLogs.add(new OrderLog("1", 1L, 2L, 3L, 4L, "PENDIENTE", LocalDateTime.now()));

        when(orderLogRepository.findByIdOrder(orderId)).thenReturn(orderLogDocuments);
        when(orderLogDocumentMapper.toOrderLogList(orderLogDocuments)).thenReturn(expectedOrderLogs);

        // Act
        List<OrderLog> result = orderLogAdapterJPA.getOrderLogsByOrderId(orderId);

        // Assert
        assertEquals(expectedOrderLogs, result);
        verify(orderLogRepository, times(1)).findByIdOrder(orderId);
        verify(orderLogDocumentMapper, times(1)).toOrderLogList(orderLogDocuments);
    }

}