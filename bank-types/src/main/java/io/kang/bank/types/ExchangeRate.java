package io.kang.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
public class ExchangeRate {
    private BigDecimal rate;
    private Currency sourceCurrency;
    private Currency targetCurrency;

    public Money exchangeTo(Money money) {
        BigDecimal targetAmount = money.getAmount();
        BigDecimal sourceAmount = targetAmount.divide(rate, RoundingMode.DOWN);
        return new Money(sourceCurrency, sourceAmount);
    }
}