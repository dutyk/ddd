package io.kang.bank.Repository.core;

import io.kang.bank.core.Aggregate;
import io.kang.bank.core.Identifier;
import io.kang.bank.types.core.EntityDiff;

public interface AggregateManager<T extends Aggregate<ID>, ID extends Identifier>  {
    void attach(T aggregate, ID id);
    void detach(T aggregate);
    T find(ID id);
    EntityDiff detectChanges(T aggregate);
}
