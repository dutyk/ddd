package io.kang.bank.domain.entity;

import io.kang.bank.core.Aggregate;
import io.kang.bank.types.AccountId;
import io.kang.bank.types.AccountNumber;
import io.kang.bank.types.Money;
import io.kang.bank.types.UserId;
import io.kang.bank.types.core.AggregateId;
import lombok.Data;

@Data
public class EAccount implements Aggregate<AggregateId> {
    private AggregateId id;
    private AccountId accountId;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;
}