package io.kang.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Money implements Serializable {
    private Currency currency;
    private BigDecimal amount;

    public Money add(Money money) {
        return new Money(currency, amount.add(money.getAmount()));
    }

    public int compareTo(Money money) {
        return this.amount.compareTo(money.amount);
    }

    public Money subtract(Money money) {
        return new Money(currency, amount.subtract(money.getAmount()));
    }
}