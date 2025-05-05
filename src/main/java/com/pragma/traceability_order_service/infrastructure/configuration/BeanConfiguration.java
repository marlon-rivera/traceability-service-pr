package com.pragma.traceability_order_service.infrastructure.configuration;

import com.pragma.traceability_order_service.application.handler.IOrderLogHandler;
import com.pragma.traceability_order_service.application.handler.impl.OrderLogHandler;
import com.pragma.traceability_order_service.domain.api.IOrderLogServicePort;
import com.pragma.traceability_order_service.domain.spi.IAutthenticatePort;
import com.pragma.traceability_order_service.domain.spi.IOrderLogPersistencePort;
import com.pragma.traceability_order_service.domain.usecase.OrderLogUseCase;
import com.pragma.traceability_order_service.infrastructure.authenticate.AuthenticateAdapter;
import com.pragma.traceability_order_service.infrastructure.out.jpa.adapter.OrderLogAdapterJPA;
import com.pragma.traceability_order_service.infrastructure.out.jpa.mapper.IOrderLogDocumentMapper;
import com.pragma.traceability_order_service.infrastructure.out.jpa.repository.IOrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOrderLogDocumentMapper orderLogDocumentMapper;
    private final IOrderLogRepository orderLogRepository;

    @Bean
    public IOrderLogPersistencePort orderLogPersistencePort(){
        return new OrderLogAdapterJPA(orderLogRepository, orderLogDocumentMapper);
    }

    @Bean
    public IAutthenticatePort autthenticatePort(){
        return new AuthenticateAdapter();
    }

    @Bean
    public IOrderLogServicePort orderLogServicePort(){
        return new OrderLogUseCase(orderLogPersistencePort(), autthenticatePort());
    }

    @Bean
    public IOrderLogHandler orderLogHandler(){
        return new OrderLogHandler(orderLogServicePort());
    }
}
