package io.kang.bank.persistence;

import io.kang.bank.domain.entity.Account;
import io.kang.bank.types.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountBuilder {
    public Account toAccount(AccountDO accountDO) {
        Account account = new Account();
        account.setId(accountDO.getId());
        account.setUserId(new UserId(String.valueOf(accountDO.getUserId())));
        account.setAccountNumber(new AccountNumber(accountDO.getAccountNumber()));
        account.setAccountId(new AccountId(accountDO.getAccountId()));
        account.setAvailable(new Money(new Currency(accountDO.getCurrency()), new BigDecimal(accountDO.getAvaliableAmount())));
        account.setDailyLimit(new Money(new Currency(accountDO.getCurrency()), new BigDecimal(accountDO.getDailyLimitAmount())));

        return account;
    }

    public AccountDO fromAccount(Account account) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(account.getId());
        accountDO.setUserId(account.getUserId().getId());
        accountDO.setAccountId(account.getAccountId().getValue());
        accountDO.setAccountNumber(account.getAccountNumber().getValue());
        accountDO.setCurrency(account.getCurrency().getValue());
        accountDO.setAvaliableAmount(account.getAvailable().getAmount().toString());
        accountDO.setDailyLimitAmount(account.getDailyLimit().getAmount().toString());

        return accountDO;
    }
}