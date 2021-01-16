package io.kang.bank.domain.service.impl;

import io.kang.bank.domain.entity.Account;
import io.kang.bank.domain.service.AccountTransferService;
import io.kang.bank.types.ExchangeRate;
import io.kang.bank.types.Money;
import org.springframework.stereotype.Component;

@Component
public class AccountTransferServiceImpl implements AccountTransferService {
    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        try {
            sourceAccount.deposit(sourceMoney);
            targetAccount.withdraw(targetMoney);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
