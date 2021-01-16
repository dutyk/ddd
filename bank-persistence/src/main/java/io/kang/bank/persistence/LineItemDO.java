package io.kang.bank.persistence;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LineItemDO {
    private Long id;
    private String orderId;
    private String itemId;
    private Long quantity;
    private BigDecimal price;
}