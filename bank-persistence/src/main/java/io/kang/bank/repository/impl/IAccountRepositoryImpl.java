package io.kang.bank.repository.impl;

import io.kang.bank.Repository.IAccountRepository;
import io.kang.bank.converter.AccountDataConverter;
import io.kang.bank.domain.entity.EAccount;
import io.kang.bank.persistence.AccountDO;
import io.kang.bank.persistence.mapper.AccountMapper;
import io.kang.bank.types.AccountDTO;
import io.kang.bank.types.AccountId;
import io.kang.bank.types.AccountNumber;
import io.kang.bank.types.core.AggregateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IAccountRepositoryImpl implements IAccountRepository {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountDataConverter accountDataConverter;

    @Override
    public Long count(AccountDTO query) {
        return Long.valueOf(accountMapper.findByUserId(query.getUserId()).size());
    }

    @Override
    public EAccount findByAccountNumberAndUserId(AccountNumber accountNumber) {
        AccountDO accountDO = accountMapper.findByAccountNumber(accountNumber.getValue());
        return accountDataConverter.fromData(accountDO);
    }


    @Override
    public void attach(EAccount aggregate) {

    }

    @Override
    public void detach(EAccount aggregate) {

    }

    @Override
    public EAccount find(AggregateId aggregateId) {
        return null;
    }

    @Override
    public void remove(EAccount aggregate) {
        AccountDO accountDO = accountDataConverter.toData(aggregate);
        accountMapper.delete(accountDO);
    }

    @Override
    public void save(EAccount aggregate) {
        if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
            // update
            AccountDO accountDO = accountDataConverter.toData(aggregate);
            accountMapper.update(accountDO);
        } else {
            // insert
            AccountDO accountDO = accountDataConverter.toData(aggregate);
            accountMapper.save(accountDO);
        }
    }
}