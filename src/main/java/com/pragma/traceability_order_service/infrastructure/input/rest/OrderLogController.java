package com.pragma.traceability_order_service.infrastructure.input.rest;

import com.pragma.traceability_order_service.application.dto.request.OrderLogCreateDto;
import com.pragma.traceability_order_service.application.dto.utils.constants.ResponsesCodes;
import com.pragma.traceability_order_service.application.handler.IOrderLogHandler;
import com.pragma.traceability_order_service.domain.model.OrderLog;
import com.pragma.traceability_order_service.infrastructure.utils.constants.openapi.OrderLogControllerOpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-log")
@RequiredArgsConstructor
public class OrderLogController {

    private final IOrderLogHandler orderLogHandler;

    @Operation(
            summary = OrderLogControllerOpenApiConstants.SAVE_ORDER_LOG_SUMMARY,
            description = OrderLogControllerOpenApiConstants.SAVE_ORDER_LOG_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = OrderLogControllerOpenApiConstants.SAVE_ORDER_LOG_RESPONSE_200_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = OrderLogControllerOpenApiConstants.SAVE_ORDER_LOG_RESPONSE_400_DESCRIPTION
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveOrderLog(@Valid @RequestBody OrderLogCreateDto orderLogCreateDto) {
        orderLogHandler.saveOrderLog(orderLogCreateDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = OrderLogControllerOpenApiConstants.GET_ORDER_LOG_SUMMARY,
            description = OrderLogControllerOpenApiConstants.GET_ORDER_LOG_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = OrderLogControllerOpenApiConstants.GET_ORDER_LOG_RESPONSE_200_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = OrderLogControllerOpenApiConstants.GET_ORDER_LOG_RESPONSE_400_DESCRIPTION
                    )
            }
    )
    @GetMapping("/{idOrder}")
    public ResponseEntity<List<OrderLog>> getOrderLog(@PathVariable Long idOrder) {
        List<OrderLog> orderLogs = orderLogHandler.getOrderLogsByOrderId(idOrder);
        return ResponseEntity.ok(orderLogs);
    }

}
