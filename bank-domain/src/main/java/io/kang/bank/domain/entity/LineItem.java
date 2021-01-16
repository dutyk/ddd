package io.kang.bank.domain.entity;

import io.kang.bank.types.ItemId;
import io.kang.bank.types.Money;
import io.kang.bank.types.OrderId;
import io.kang.bank.types.Quantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem implements Serializable {
    private OrderId orderId;
    private ItemId itemId;
    private Quantity quantity;
    private Money price;

    @Override
    public boolean equals(Object obj) {
        LineItem lineItem = (LineItem) obj;

        return lineItem.getItemId().equals(itemId);
    }
}