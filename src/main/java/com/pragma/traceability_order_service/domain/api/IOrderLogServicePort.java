package com.pragma.traceability_order_service.domain.api;

import com.pragma.traceability_order_service.domain.model.OrderLog;

import java.util.List;

public interface IOrderLogServicePort {

    void saveOrderLog(Long idOrder, Long idClient, Long idRestaurant, Long idEmployee, String status);
    List<OrderLog> getOrderLogsByIdOrder(Long idOrder);

}
