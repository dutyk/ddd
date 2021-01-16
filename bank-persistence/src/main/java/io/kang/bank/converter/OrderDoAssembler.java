package io.kang.bank.converter;

import io.kang.bank.domain.entity.Order;
import io.kang.bank.persistence.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDoAssembler {
    OrderDoAssembler INSTANCE = Mappers.getMapper(OrderDoAssembler.class);

    @Mapping(source = "userId.id", target = "userId")
    @Mapping(source = "orderId.id", target = "orderId")
    @Mapping(source = "orderState", target = "orderState")
    @Mapping(source = "totalPrice.amount", target = "totalPrice")
    @Mapping(source = "id.value", target = "id")
    OrderDO toDO(Order order);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "userId.id", source = "userId")
    @Mapping(target = "orderId.id", source = "orderId")
    @Mapping(target = "orderState", source = "orderState")
    @Mapping(target = "totalPrice.amount", source = "totalPrice")
    @Mapping(target = "lineItems", ignore = true)
    Order toEntity(OrderDO orderDO);
}