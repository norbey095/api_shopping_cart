package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;
import com.emazon.api_shopping_cart.domain.spi.ICartTransactionPersistencePort;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientTransaction;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CartTransactionAdapter implements ICartTransactionPersistencePort {

    private final IFeignClientTransaction feignClientTransaction;

    @Override
    public void saveBuy(TransactionRequest transactionRequest) {
        feignClientTransaction.saveBuy(transactionRequest);
    }
}
