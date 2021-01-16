package io.kang.bank.domain.service;

import io.kang.bank.domain.entity.Account;
import io.kang.bank.types.ExchangeRate;
import io.kang.bank.types.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}