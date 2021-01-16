package io.kang.bank.application;

import io.kang.bank.domain.entity.Account;
import io.kang.bank.types.*;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountDtoAssemblerTest {
    @Test
    public void testAccountAssembler() {
        Account account = new Account();
        account.setId(1L);
        account.setUserId(new UserId("1"));
        account.setAvailable(new Money(new Currency("CNY"), new BigDecimal(100)));
        account.setDailyLimit(new Money(new Currency("CNY"), new BigDecimal(100)));
        account.setAccountNumber(new AccountNumber("1"));
        account.setAccountId(new AccountId("1"));

        AccountDTO accountDTO = AccountDtoAssembler.INSTANCE.toDTO(account);

        System.out.println(accountDTO);
    }

    @Test
    public void testAccountAssembler1() {
        AccountDTO accountDto = new AccountDTO();
        accountDto.setId(1L);
        accountDto.setUserId("1");
        accountDto.setAvailable(new BigDecimal(100));
        accountDto.setDailyLimit(new BigDecimal(100));
        accountDto.setAccountNumber("1");
        accountDto.setAccountId("1");
        accountDto.setCurrency("CNY");

        Account account = AccountDtoAssembler.INSTANCE.toEntity(accountDto);

        System.out.println(account);
    }
}