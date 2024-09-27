package com.emazon.api_shopping_cart.infraestructure.configuration.feign;

import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = ConstantsConfig.TRANSACTION, url = ConstantsConfig.URL_TRANSACTION_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientTransaction {

    @GetMapping(ConstantsConfig.URL_TRANSACTION_SALES)
    void saveBuy(@RequestBody TransactionRequest transactionRequest);
}
