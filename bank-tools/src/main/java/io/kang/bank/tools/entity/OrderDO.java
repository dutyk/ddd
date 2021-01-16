package io.kang.bank.tools.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "order"
)
public class OrderDO {
    @Column(
            nullable = false,
            updatable = false
    )
    @Id
    private Long id;

    @Column(
            nullable = false,
            updatable = false
    )
    private String userId;

    @Column(
            nullable = false,
            updatable = false
    )
    private String orderId;

    @Enumerated(EnumType.ORDINAL)
    @Column(
            nullable = false,
            updatable = false
    )
    private OrderState orderState;

    @Column(
            nullable = false,
            updatable = false,
            precision = 20,
            scale = 10
    )
    private BigDecimal totalPrice;
}
