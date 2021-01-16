package io.kang.bank.external;

import io.kang.bank.types.Currency;
import io.kang.bank.types.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}