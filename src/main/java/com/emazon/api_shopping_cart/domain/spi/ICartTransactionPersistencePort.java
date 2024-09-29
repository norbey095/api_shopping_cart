package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.transaction.TransactionRequest;

import java.time.LocalDateTime;
import java.util.List;


public interface ICartTransactionPersistencePort {

      void saveBuy(List<TransactionRequest> transactionRequest);

      void returnRecord(String userName, LocalDateTime localDateTime);

}
