package io.kang.bank.converter;

import io.kang.bank.domain.entity.EAccount;
import io.kang.bank.persistence.AccountDO;
import org.springframework.stereotype.Component;

@Component
public class AccountDataConverter {
    private AccountDoAssembler accountDoAssembler;

    public AccountDataConverter() {
        this.accountDoAssembler = AccountDoAssembler.INSTANCE;
    }

    public EAccount fromData(AccountDO accountDO) {
        return this.accountDoAssembler.toEntity(accountDO);
    }

    public AccountDO toData(EAccount account) {
        return this.accountDoAssembler.toDO(account);
    }

}