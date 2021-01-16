package io.kang.bank.application;

import java.math.BigDecimal;

public interface TransferService {
    Boolean transfer(String sourceAccountId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);
}
