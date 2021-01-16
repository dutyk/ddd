package io.kang.bank.persistence;

import io.kang.bank.types.OrderState;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderDO {
    private Long id;
    private String userId;
    private String orderId;
    private OrderState orderState;
    private BigDecimal totalPrice;
}
