package com.pragma.traceability_order_service.infrastructure.out.jpa.adapter;

import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.domain.spi.IOrderLogPersistencePort;
import com.pragma.traceability_order_service.infrastructure.out.jpa.entity.OrderLogDocument;
import com.pragma.traceability_order_service.infrastructure.out.jpa.mapper.IOrderLogDocumentMapper;
import com.pragma.traceability_order_service.infrastructure.out.jpa.repository.IOrderLogRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderLogAdapterJPA implements IOrderLogPersistencePort {

    private final IOrderLogRepository orderLogRepository;
    private final IOrderLogDocumentMapper orderLogDocumentMapper;

    @Override
    public void saveOrderLog(OrderLog orderLog) {
        orderLogRepository.save(
                orderLogDocumentMapper.toOrderLogDocument(orderLog)
        );
    }

    @Override
    public List<OrderLog> getOrderLogsByOrderId(Long orderId) {
        return orderLogDocumentMapper.toOrderLogList(
                orderLogRepository.findByIdOrder(orderId)
        );
    }
}
