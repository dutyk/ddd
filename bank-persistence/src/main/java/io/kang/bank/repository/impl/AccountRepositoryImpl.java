package io.kang.bank.repository.impl;

import io.kang.bank.Repository.AccountRepository;
import io.kang.bank.domain.entity.Account;
import io.kang.bank.persistence.AccountBuilder;
import io.kang.bank.persistence.AccountDO;
import io.kang.bank.persistence.mapper.AccountMapper;
import io.kang.bank.types.AccountId;
import io.kang.bank.types.AccountNumber;
import io.kang.bank.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private AccountMapper accountDAO;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.findByAccountId(id.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.findByAccountNumber(accountNumber.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        if (accountDO.getUserId() == null) {
            accountDAO.save(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return accountBuilder.toAccount(accountDO);
    }
}