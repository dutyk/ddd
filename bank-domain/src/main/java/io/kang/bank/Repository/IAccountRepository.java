package io.kang.bank.Repository;

import io.kang.bank.core.Repository;
import io.kang.bank.domain.entity.EAccount;
import io.kang.bank.types.AccountDTO;
import io.kang.bank.types.AccountId;
import io.kang.bank.types.AccountNumber;
import io.kang.bank.types.core.AggregateId;

public interface IAccountRepository extends Repository<EAccount, AggregateId> {
    // 自定义Count接口，在这里OrderQuery是一个自定义的DTO
    Long count(AccountDTO query);

    EAccount findByAccountNumberAndUserId(AccountNumber accountNumber);
}
