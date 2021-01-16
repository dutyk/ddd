package io.kang.bank.tools.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(
        name = "item"
)
public class LineItemDO {
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
    private String orderId;

    @Column(
            nullable = false,
            updatable = false
    )
    private String itemId;

    @Column(
            nullable = false,
            updatable = false
    )
    private Long quantity;

    @Column(
            nullable = false,
            updatable = false,
            precision = 20,
            scale = 10
    )
    private BigDecimal price;
}