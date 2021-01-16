package io.kang.bank.converter;

import io.kang.bank.domain.entity.LineItem;
import io.kang.bank.persistence.LineItemDO;
import org.springframework.stereotype.Component;

@Component
public class LineItemDataConverter {
    private LineItemDoAssembler lineItemDoAssembler;

    public LineItemDataConverter() {
        this.lineItemDoAssembler = LineItemDoAssembler.INSTANCE;
    }

    public LineItem fromData(LineItemDO lineItemDO) {
        return this.lineItemDoAssembler.toEntity(lineItemDO);
    }

    public LineItemDO toData(LineItem lineItem) {
        return this.lineItemDoAssembler.toDO(lineItem);
    }

}