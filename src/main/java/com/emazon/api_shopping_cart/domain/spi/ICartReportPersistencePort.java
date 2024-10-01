package com.emazon.api_shopping_cart.domain.spi;

import com.emazon.api_shopping_cart.domain.model.report.ReportBuy;


public interface ICartReportPersistencePort {

      void saveReport(ReportBuy reportBuy);

}
