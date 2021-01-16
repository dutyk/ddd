package io.kang.bank.persistence;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountDO {
    private Long id;
    private String userId;
    private String accountId;
    private String accountNumber;
    private String currency;
    private String avaliableAmount;
    private String dailyLimitAmount;
}
