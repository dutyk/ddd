package io.kang.bank.domain.entity;

import io.kang.bank.exception.DailyLimitExceededException;
import io.kang.bank.exception.InsufficientFundsException;
import io.kang.bank.exception.InvalidCurrencyException;
import io.kang.bank.types.*;
import lombok.Data;

@Data
public class Account {
    private Long id;
    private AccountId accountId;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    public Currency getCurrency() {
        return this.available.getCurrency();
    }


    public void withdraw(Money money) throws Exception {
        if (this.available.compareTo(money) < 0) {
            throw new InsufficientFundsException();
        }
        if (this.dailyLimit.compareTo(money) < 0) {
            throw new DailyLimitExceededException();
        }
        this.available = this.available.subtract(money);
    }

    public void deposit(Money money) throws InvalidCurrencyException {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencyException();
        }

        this.available = this.available.add(money);
    }
}