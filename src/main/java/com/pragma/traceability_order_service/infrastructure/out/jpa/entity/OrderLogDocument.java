package com.pragma.traceability_order_service.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "order_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderLogDocument {

    @Id
    private String id;
    private Long idOrder;
    private Long idClient;
    private Long idRestaurant;
    private Long idEmployee;
    private String status;
    private LocalDateTime timestamp;

}
