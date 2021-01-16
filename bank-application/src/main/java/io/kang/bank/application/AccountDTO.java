package io.kang.bank.application;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;
    private String accountId;
    private String accountNumber;
    private String userId;
    private BigDecimal available;
    private BigDecimal dailyLimit;
    private String currency;
}
