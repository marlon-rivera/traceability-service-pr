package com.pragma.traceability_order_service.application.dto.utils.constants;

public class OrderLogCreateDtoConstants {

    private OrderLogCreateDtoConstants() {
        // Prevent instantiation
    }

    public static final String ID_ORDER_NOT_NULL = "The order ID cannot be null";
    public static final String ID_ORDER_DESCRIPTION = "The order ID must be a positive number";
    public static final String ID_ORDER_EXAMPLE = "1";

    public static final String ID_CLIENT_NOT_NULL = "The client ID cannot be null";
    public static final String ID_CLIENT_DESCRIPTION = "The client ID must be a positive number";
    public static final String ID_CLIENT_EXAMPLE = "1";

    public static final String STATUS_NOT_NULL = "The status cannot be null";
    public static final String STATUS_DESCRIPTION = "The status must be a valid format";
    public static final String STATUS_EXAMPLE = "DELIVERED";

    public static final String ID_RESTAURANT_NOT_NULL = "The restaurant ID cannot be null";
    public static final String ID_RESTAURANT_DESCRIPTION = "The restaurant ID must be a positive number";
    public static final String ID_RESTAURANT_EXAMPLE = "1";

    public static final String ID_EMPLOYEE_NOT_NULL = "The employee ID cannot be null";
    public static final String ID_EMPLOYEE_DESCRIPTION = "The employee ID must be a positive number";
    public static final String ID_EMPLOYEE_EXAMPLE = "1";

}
