package io.kang.bank.application;

import io.kang.bank.domain.entity.Account;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface AccountDtoAssembler {
    AccountDtoAssembler INSTANCE = Mappers.getMapper(AccountDtoAssembler.class);

    // 在这里只需要指出字段不一致的情况，支持复杂嵌套
    @Mapping(source = "accountNumber.value", target = "accountNumber")
    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "userId.id", target = "userId")
    @Mapping(source = "available.amount", target = "available")
    @Mapping(source = "dailyLimit.amount", target = "dailyLimit")
    @Mapping(source = "dailyLimit.currency.value", target = "currency")
    AccountDTO toDTO(Account account);

    // 如果字段没有不一致，一行注解都不需要
    @Mapping(target = "accountNumber.value", source = "accountNumber")
    @Mapping(target = "accountId.value", source = "accountId")
    @Mapping(target = "userId.id", source = "userId")
    @Mapping(target = "available.amount", source = "available")
    @Mapping(target = "available.currency.value", source = "currency")
    @Mapping(target = "dailyLimit.amount", source = "dailyLimit")
    @Mapping(target = "dailyLimit.currency.value", source = "currency")
    Account toEntity(AccountDTO accountDTO);
}