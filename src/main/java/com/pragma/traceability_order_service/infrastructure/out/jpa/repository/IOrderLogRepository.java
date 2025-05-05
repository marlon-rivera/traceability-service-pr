package com.pragma.traceability_order_service.infrastructure.out.jpa.repository;

import com.pragma.traceability_order_service.infrastructure.out.jpa.entity.OrderLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IOrderLogRepository extends MongoRepository<OrderLogDocument, String> {

    List<OrderLogDocument> findByIdOrder(Long idOrder);

}
