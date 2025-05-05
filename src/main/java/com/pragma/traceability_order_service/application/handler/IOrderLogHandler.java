package com.pragma.traceability_order_service.application.handler;

import com.pragma.traceability_order_service.application.dto.request.OrderLogCreateDto;
import com.pragma.traceability_order_service.domain.model.OrderLog;

import java.util.List;

public interface IOrderLogHandler {

    void saveOrderLog(OrderLogCreateDto orderLogCreateDto);
    List<OrderLog> getOrderLogsByOrderId(Long orderId);
}
