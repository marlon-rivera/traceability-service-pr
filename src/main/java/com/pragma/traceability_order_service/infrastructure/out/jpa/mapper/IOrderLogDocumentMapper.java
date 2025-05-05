package com.pragma.traceability_order_service.infrastructure.out.jpa.mapper;

import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.infrastructure.out.jpa.entity.OrderLogDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface IOrderLogDocumentMapper {

    OrderLogDocument toOrderLogDocument(OrderLog orderLog);
    OrderLog toOrderLog(OrderLogDocument orderLogDocument);
    List<OrderLog> toOrderLogList(List<OrderLogDocument> orderLogDocumentList);

}
