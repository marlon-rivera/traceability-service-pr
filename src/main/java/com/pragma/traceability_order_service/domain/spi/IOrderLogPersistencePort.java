package com.pragma.traceability_order_service.domain.spi;

import com.pragma.traceability_order_service.domain.model.OrderLog;

import java.util.List;

public interface IOrderLogPersistencePort {

    void saveOrderLog(OrderLog orderLog);
    List<OrderLog> getOrderLogsByOrderId(Long orderId);

}
