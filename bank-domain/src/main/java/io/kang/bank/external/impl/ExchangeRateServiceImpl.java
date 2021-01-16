package io.kang.bank.external.impl;

import io.kang.bank.external.ExchangeRateService;
import io.kang.bank.external.YahooForexService;
import io.kang.bank.types.Currency;
import io.kang.bank.types.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    private YahooForexService yahooForexService;

    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal forex = yahooForexService.getExchangeRate(source.getValue(), target.getValue());
        return new ExchangeRate(forex, source, target);
    }
}