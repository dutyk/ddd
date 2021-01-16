package io.kang.bank.Repository;

import io.kang.bank.domain.entity.Account;
import io.kang.bank.types.AccountId;
import io.kang.bank.types.AccountNumber;

public interface AccountRepository {
    Account find(AccountId accountId);
    Account find(AccountNumber accountNumber);
    Account save(Account account);
}