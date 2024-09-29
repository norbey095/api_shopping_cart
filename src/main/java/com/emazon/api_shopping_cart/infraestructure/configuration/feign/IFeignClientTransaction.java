package com.emazon.api_shopping_cart.infraestructure.configuration.feign;

import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@FeignClient(name = ConstantsConfig.TRANSACTION, url = ConstantsConfig.URL_TRANSACTION_PORT
        , configuration = FeignConfig.class)
public interface IFeignClientTransaction {

    @PostMapping(ConstantsConfig.URL_TRANSACTION_SALES)
    void saveBuy(@RequestBody List<TransactionRequest> transactionRequest);

    @DeleteMapping(ConstantsConfig.URL_DELETE_SALES)
    void returnRecord(@PathVariable String userName,@PathVariable LocalDateTime buyDate);

}
