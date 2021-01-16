package io.kang.bank.external.impl;

import io.kang.bank.external.YahooForexService;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class YahooForexServiceImpl implements YahooForexService {
    private final static Map<String, BigDecimal> EXCHANGE_MAP = new HashMap<>();
    static {
        EXCHANGE_MAP.put("CNY-USD", new BigDecimal(1.0/6.7));
        EXCHANGE_MAP.put("USD-CNY", new BigDecimal(6.7));
        EXCHANGE_MAP.put("CNY-JPY", new BigDecimal(15.8781));
    }
    @Override
    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        return EXCHANGE_MAP.get(sourceCurrency+"-"+targetCurrency);
    }
}