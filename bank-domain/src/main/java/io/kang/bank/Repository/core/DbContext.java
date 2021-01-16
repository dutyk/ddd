package io.kang.bank.Repository.core;

import io.kang.bank.core.Aggregate;
import io.kang.bank.core.Identifier;
import io.kang.bank.types.core.EntityDiff;
import lombok.Getter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DbContext <T extends Aggregate<ID>, ID extends Identifier> {

    @Getter
    private Class<? extends T> aggregateClass;

    private Map<ID, T> aggregateMap = new ConcurrentHashMap<>();

    public DbContext(Class<? extends T> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    public void attach(T aggregate) {
        if (aggregate.getId() != null) {
            if (!aggregateMap.containsKey(aggregate.getId())) {
                this.merge(aggregate);
            }
        }
    }

    public void detach(T aggregate) {
        if (aggregate.getId() != null) {
            aggregateMap.remove(aggregate.getId());
        }
    }

    public EntityDiff detectChanges(T aggregate) {
        if (aggregate.getId() == null) {
            return EntityDiff.EMPTY;
        }
        T snapshot = aggregateMap.get(aggregate.getId());
        if (snapshot == null) {
            attach(aggregate);
        }
        return DiffUtils.diff(snapshot, aggregate);
    }

    public T find(ID id) {
        return aggregateMap.get(id);
    }

    public void merge(T aggregate) {
        if (aggregate.getId() != null) {
            T snapshot = SnapshotUtils.snapshot(aggregate);
            aggregateMap.put(aggregate.getId(), snapshot);
        }
    }

    public void setId(T aggregate, ID id) {
        ReflectionUtils.writeField("id", aggregate, id);
    }
}
