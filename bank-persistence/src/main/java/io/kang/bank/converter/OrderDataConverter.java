package io.kang.bank.converter;

import io.kang.bank.domain.entity.Order;
import io.kang.bank.persistence.OrderDO;
import org.springframework.stereotype.Component;

@Component
public class OrderDataConverter {
    private OrderDoAssembler orderDoAssembler;

    public OrderDataConverter() {
        this.orderDoAssembler = OrderDoAssembler.INSTANCE;
    }

    public Order fromData(OrderDO orderDO) {
        return this.orderDoAssembler.toEntity(orderDO);
    }

    public OrderDO toData(Order order) {
        return this.orderDoAssembler.toDO(order);
    }

}