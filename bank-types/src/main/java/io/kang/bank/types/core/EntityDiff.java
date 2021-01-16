package io.kang.bank.types.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityDiff {
    public static final EntityDiff EMPTY = new EntityDiff(false, true);
    private final Map<String, Diff> diffs = new ConcurrentHashMap();

    private boolean selfModified;
    private boolean empty;

    public Diff getDiff(String key) {
        return this.diffs.get(key);
    }

    public void setDiff(String key, Diff diff) {
        this.diffs.put(key, diff);
    }
}