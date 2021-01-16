package io.kang.bank.Repository.core;

import io.kang.bank.core.Aggregate;
import io.kang.bank.core.Identifier;
import io.kang.bank.types.core.EntityDiff;

public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T,ID>  {

    private ThreadLocal<DbContext<T, ID>> context;
    private Class<? extends T> targetClass;

    public ThreadLocalAggregateManager(Class targetClass) {
        this.targetClass = targetClass;
        this.context = ThreadLocal.withInitial(() -> new DbContext<>(targetClass));
    }

    public void attach(T aggregate) {
        context.get().attach(aggregate);
    }

    @Override
    public void attach(T aggregate, ID id) {
        context.get().setId(aggregate, id);
        context.get().attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        context.get().detach(aggregate);
    }

    @Override
    public T find(ID id) {
        return context.get().find(id);
    }

    @Override
    public EntityDiff detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }

    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }

//    public void setId(T aggregate, ID id) {
//        ReflectionUtils.writeField("eid", aggregate, id);
//    }
}
