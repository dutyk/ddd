package io.kang.bank.converter;

import io.kang.bank.domain.entity.LineItem;
import io.kang.bank.persistence.LineItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LineItemDoAssembler {
    LineItemDoAssembler INSTANCE = Mappers.getMapper(LineItemDoAssembler.class);

    @Mapping(source = "orderId.id", target = "orderId")
    @Mapping(source = "itemId.id", target = "itemId")
    @Mapping(source = "quantity.value", target = "quantity")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(target = "id", ignore = true)
    LineItemDO toDO(LineItem lineItem);

    @Mapping(target = "orderId.id", source = "orderId")
    @Mapping(target = "itemId.id", source = "itemId")
    @Mapping(target = "quantity.value", source = "quantity")
    @Mapping(target = "price.amount", source = "price")
    LineItem toEntity(LineItemDO lineItemDO);
}