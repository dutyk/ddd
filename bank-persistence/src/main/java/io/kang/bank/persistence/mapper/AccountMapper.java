package io.kang.bank.persistence.mapper;


import io.kang.bank.persistence.AccountDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountDO findByAccountId(String accountId);
    AccountDO findByAccountNumber(String accountNumber);
    List<AccountDO> findByUserId(Long userId);
    void save(AccountDO accountDO);
    void update(AccountDO accountDO);
    void delete(AccountDO accountDO);
}