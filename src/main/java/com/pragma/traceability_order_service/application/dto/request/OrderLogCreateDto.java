package com.pragma.traceability_order_service.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.pragma.traceability_order_service.application.dto.utils.constants.OrderLogCreateDtoConstants;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLogCreateDto {

    @Schema(
            description = OrderLogCreateDtoConstants.ID_ORDER_DESCRIPTION,
            example = OrderLogCreateDtoConstants.ID_ORDER_EXAMPLE
    )
    @NotNull(message = OrderLogCreateDtoConstants.ID_ORDER_NOT_NULL)
    private Long idOrder;

    @Schema(
            description = OrderLogCreateDtoConstants.ID_CLIENT_DESCRIPTION,
            example = OrderLogCreateDtoConstants.ID_CLIENT_EXAMPLE
    )
    @NotNull(message = OrderLogCreateDtoConstants.ID_CLIENT_NOT_NULL)
    private Long idClient;

    @Schema(
            description = OrderLogCreateDtoConstants.ID_RESTAURANT_DESCRIPTION,
            example = OrderLogCreateDtoConstants.ID_RESTAURANT_EXAMPLE
    )
    @NotNull(message = OrderLogCreateDtoConstants.ID_RESTAURANT_NOT_NULL)
    private Long idRestaurant;

    @Schema(
            description = OrderLogCreateDtoConstants.ID_EMPLOYEE_DESCRIPTION,
            example = OrderLogCreateDtoConstants.ID_EMPLOYEE_EXAMPLE
    )
     private Long idEmployee;

    @Schema(
            description = OrderLogCreateDtoConstants.STATUS_DESCRIPTION,
            example = OrderLogCreateDtoConstants.STATUS_EXAMPLE
    )
    @NotBlank(message = OrderLogCreateDtoConstants.STATUS_NOT_NULL)
    private String status;

}
