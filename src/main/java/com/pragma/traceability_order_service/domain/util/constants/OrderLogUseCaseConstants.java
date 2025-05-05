package com.pragma.traceability_order_service.domain.util.constants;

public class OrderLogUseCaseConstants {

    private OrderLogUseCaseConstants() {
        // Prevent instantiation
    }

    public static final String ORDER_NOT_FOUND = "Order does not have a log, because it does not exist";
    public static final String ORDER_NOT_BELONG_TO_CLIENT = "Order does not belong to the client";
    public static final int FIRST_POSITION = 0;

}
