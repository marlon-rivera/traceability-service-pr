package com.pragma.traceability_order_service.domain.usecase;

import com.pragma.traceability_order_service.domain.api.IOrderLogServicePort;
import com.pragma.traceability_order_service.domain.exception.InvalidDataException;
import com.pragma.traceability_order_service.domain.exception.ResourceNotFoundException;
import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.domain.spi.IAutthenticatePort;
import com.pragma.traceability_order_service.domain.spi.IOrderLogPersistencePort;
import com.pragma.traceability_order_service.domain.util.constants.OrderLogUseCaseConstants;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class OrderLogUseCase implements IOrderLogServicePort {

    private final IOrderLogPersistencePort orderLogPersistencePort;
    private final IAutthenticatePort autthenticatePort;

    @Override
    public void saveOrderLog(Long idOrder, Long idClient, Long idRestaurant, Long idEmployee, String status) {
        OrderLog orderLog = new OrderLog(null, idOrder, idClient, idRestaurant, idEmployee, status, LocalDateTime.now());
        orderLogPersistencePort.saveOrderLog(orderLog);
    }

    @Override
    public List<OrderLog> getOrderLogsByIdOrder(Long idOrder) {
        List<OrderLog> orderLogs = orderLogPersistencePort.getOrderLogsByOrderId(idOrder);
        if (orderLogs.isEmpty()) {
            throw new ResourceNotFoundException(OrderLogUseCaseConstants.ORDER_NOT_FOUND);
        }
        Long idClient = autthenticatePort.getCurrentUserId();
        if(!orderLogs.get(OrderLogUseCaseConstants.FIRST_POSITION).getIdClient().equals(idClient)) {
            throw new InvalidDataException(OrderLogUseCaseConstants.ORDER_NOT_BELONG_TO_CLIENT);
        }
        return orderLogPersistencePort.getOrderLogsByOrderId(idOrder);
    }
}
