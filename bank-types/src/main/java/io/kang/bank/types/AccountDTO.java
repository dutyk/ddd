package io.kang.bank.types;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AccountDTO {
    private Long id;
    private String accountId;
    private String accountNumber;
    private Long userId;
    private BigDecimal available;
    private BigDecimal dailyLimit;
    private String currency;
}