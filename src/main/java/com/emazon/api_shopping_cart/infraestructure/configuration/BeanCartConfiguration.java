package com.emazon.api_shopping_cart.infraestructure.configuration;

import com.emazon.api_shopping_cart.domain.api.ICartServicePort;
import com.emazon.api_shopping_cart.domain.spi.*;
import com.emazon.api_shopping_cart.domain.usecase.CartUseCase;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientReport;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientTransaction;
import com.emazon.api_shopping_cart.infraestructure.output.adapter.*;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartEntityMapper;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartReportMapper;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartStockMapper;
import com.emazon.api_shopping_cart.infraestructure.output.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanCartConfiguration {

    private final ICartRepository cartRepository;
    private final ICartEntityMapper cartEntityMapper;

    @Bean
    public ICartPersistencePort cartPersistencePort() {
        return new CartJpaAdapter(cartRepository, cartEntityMapper);
    }

    @Bean
    public IAthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationAdapter();
    }

    @Bean
    public ICartStockPersistencePort cartStockPersistencePort(IFeignClientStock feignClientStock,
                                                              ICartStockMapper cartStockMapper) {
        return new CartStockAdapter(feignClientStock,cartStockMapper);
    }

    @Bean
    public ICartTransactionPersistencePort cartTransactionPersistencePort(IFeignClientTransaction feignClientTransaction) {
        return new CartTransactionAdapter(feignClientTransaction);
    }

    @Bean
    public ICartReportPersistencePort cartReportPersistencePort(IFeignClientReport feignClientReport,
                                                                ICartReportMapper cartReportMapper) {
        return new CartReportAdapter(feignClientReport,cartReportMapper);
    }

    @Bean
    public ICartServicePort cartSaveServicePort(ICartStockPersistencePort cartStockPersistencePort,
                                                ICartPersistencePort cartSavePersistencePort,
                                                IAthenticationPersistencePort authenticationPersistencePort,
                                                ICartTransactionPersistencePort cartTransactionPersistencePort,
                                                ICartReportPersistencePort cartReportPersistencePort) {
        return new CartUseCase(cartSavePersistencePort,cartStockPersistencePort,
                authenticationPersistencePort,cartTransactionPersistencePort,cartReportPersistencePort);
    }
}
