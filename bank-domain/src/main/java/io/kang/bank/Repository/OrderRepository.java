package io.kang.bank.Repository;

import io.kang.bank.domain.entity.Order;

public interface OrderRepository {
    Long countItemList(Order order);
}