package com.pragma.traceability_order_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLog {

    private String id;
    private Long idOrder;
    private Long idClient;
    private Long idRestaurant;
    private Long idEmployee;
    private String status;
    private LocalDateTime timestamp;

}
