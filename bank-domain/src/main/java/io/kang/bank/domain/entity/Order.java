package io.kang.bank.domain.entity;

import io.kang.bank.core.Aggregate;
import io.kang.bank.types.*;
import io.kang.bank.types.core.AggregateId;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order implements Aggregate<AggregateId> {
    private AggregateId id;
    private UserId userId;
    private OrderId orderId;
    private List<LineItem> lineItems;
    private OrderState orderState;
    private Money totalPrice;

    public void addLineItem(ItemId itemId, Quantity quantity, Money price) {
        if(this.lineItems == null) {
            this.lineItems = new ArrayList<>();
        }
        this.lineItems.add(new LineItem(orderId, itemId, quantity, price));
    }

    public void pay() {
        this.orderState = OrderState.PAID;
    }

    public void calTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        Currency currency = null;

        if(lineItems == null) {
            return;
        }

        for(LineItem lineItem : lineItems) {
            total.add(lineItem.getPrice().getAmount().multiply(new BigDecimal(lineItem.getQuantity().getValue())));
            currency = lineItem.getPrice().getCurrency();
        }

        this.totalPrice = new Money(currency, total);
    }
}