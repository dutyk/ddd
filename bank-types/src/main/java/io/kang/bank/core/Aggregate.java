package io.kang.bank.core;

// 聚合根的Marker接口
public interface Aggregate<ID extends Identifier> extends Entity<ID> {
}