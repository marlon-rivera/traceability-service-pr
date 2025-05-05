package com.pragma.traceability_order_service.application.handler.impl;

import com.pragma.traceability_order_service.application.dto.request.OrderLogCreateDto;
import com.pragma.traceability_order_service.application.handler.IOrderLogHandler;
import com.pragma.traceability_order_service.domain.api.IOrderLogServicePort;
import com.pragma.traceability_order_service.domain.model.OrderLog;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderLogHandler implements IOrderLogHandler {

    private final IOrderLogServicePort orderLogServicePort;

    @Override
    public void saveOrderLog(OrderLogCreateDto orderLogCreateDto) {
        orderLogServicePort.saveOrderLog(orderLogCreateDto.getIdOrder(),
                orderLogCreateDto.getIdClient(), orderLogCreateDto.getIdRestaurant(), orderLogCreateDto.getIdEmployee(), orderLogCreateDto.getStatus());
    }

    @Override
    public List<OrderLog> getOrderLogsByOrderId(Long orderId) {
        return orderLogServicePort.getOrderLogsByIdOrder(orderId);
    }

}
