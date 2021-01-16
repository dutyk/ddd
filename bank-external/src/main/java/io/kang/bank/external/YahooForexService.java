package io.kang.bank.external;

import java.math.BigDecimal;

public interface YahooForexService {
    BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency);
}