package io.kang.bank.application.impl;

import io.kang.bank.Repository.AccountRepository;
import io.kang.bank.application.TransferService;
import io.kang.bank.domain.entity.Account;
import io.kang.bank.domain.service.AccountTransferService;
import io.kang.bank.domain.types.AuditMessage;
import io.kang.bank.external.ExchangeRateService;
import io.kang.bank.messaging.AuditMessageProducer;
import io.kang.bank.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TransferServiceImpl implements TransferService {
    @Autowired
    AccountTransferService accountTransferService;

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuditMessageProducer auditMessageProducer;

    @Override
    public Boolean transfer(String sourceAccountId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {
        // 参数校验
        Money targetMoney = new Money(new Currency(targetCurrency), targetAmount);

        // 读数据
        Account sourceAccount = accountRepository.find(new AccountId(sourceAccountId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));

        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 业务逻辑
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);

        return true;
    }
}
