package io.kang.bank.Repository.core;

import io.kang.bank.core.Aggregate;
import io.kang.bank.core.Identifier;
import io.kang.bank.types.core.Diff;
import io.kang.bank.types.core.DiffType;
import io.kang.bank.types.core.EntityDiff;
import io.kang.bank.types.core.ListDiff;

import java.util.ArrayList;
import java.util.List;

public class DiffUtils {
    public static<T extends Aggregate<ID>, ID extends Identifier> EntityDiff diff(T snapshot, T aggregate) {
        EntityDiff entityDiff = new EntityDiff();
        entityDiff.setSelfModified(false);
        entityDiff.setEmpty(true);

        List<String> keys = ReflectionUtils.getKeys(snapshot);
        for(String key: keys) {
            try {
                Object snapshotVal = ReflectionUtils.getFieldValue(snapshot, key);
                Object aggregateVal = ReflectionUtils.getFieldValue(aggregate, key);

                if(snapshotVal instanceof List) {
                    ListDiff listDiff = dealListDiff((List) snapshotVal, (List) aggregateVal);
                    if(listDiff.getDiffs().isEmpty()) {
                        entityDiff.setEmpty(false);
                    }
                    entityDiff.setDiff(key, listDiff);
                    continue;
                }

                if(!snapshotVal.equals(aggregateVal)) {
                    entityDiff.setSelfModified(true);
                    entityDiff.setEmpty(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return entityDiff;
    }

    private static ListDiff dealListDiff(List snapshotListVal, List aggregateListVal)throws Exception{
        ListDiff listDiff = new ListDiff();
        List<Diff> diffs = new ArrayList<>();

        if(aggregateListVal == null || aggregateListVal.size() == 0) {
            listDiff.setDiffs(diffs);
            return listDiff;
        }

        for(Object obj: aggregateListVal) {
            int pos = snapshotListVal.indexOf(obj);

            if(pos != -1) {
                boolean isDiff = compareValus(snapshotListVal.get(pos), obj);
                if(isDiff) {
                    Diff diff = new Diff();
                    diff.setOldValue(snapshotListVal.get(pos));
                    diff.setNewValue(obj);
                    diff.setType(DiffType.Modified);
                    diffs.add(diff);
                }
            }else {
                Diff diff = new Diff();
                diff.setOldValue(null);
                diff.setNewValue(obj);
                diff.setType(DiffType.Added);
                diffs.add(diff);
            }
        }
        listDiff.setDiffs(diffs);

        return listDiff;
    }

    private static boolean compareValus(Object oldValue, Object newValue)throws Exception {
        List<String> keys = ReflectionUtils.getKeys(oldValue);
        boolean flag = false;
        for(String key: keys) {
            Object oldV = ReflectionUtils.getFieldValue(oldValue, key);
            Object newV = ReflectionUtils.getFieldValue(newValue, key);
            if(!oldV.equals(newV)) {
                return true;
            }
        }

        return flag;
    }
}