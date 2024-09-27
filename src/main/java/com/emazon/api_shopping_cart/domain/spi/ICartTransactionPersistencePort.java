package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;


public interface ICartTransactionPersistencePort {

      void saveBuy(TransactionRequest transactionRequest);

}
