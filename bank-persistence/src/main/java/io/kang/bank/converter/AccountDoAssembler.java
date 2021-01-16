package io.kang.bank.converter;

import io.kang.bank.domain.entity.EAccount;
import io.kang.bank.persistence.AccountDO;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface AccountDoAssembler {
    AccountDoAssembler INSTANCE = Mappers.getMapper(AccountDoAssembler.class);

    // 在这里只需要指出字段不一致的情况，支持复杂嵌套
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "accountNumber.value", target = "accountNumber")
    @Mapping(source = "accountId.value", target = "accountId")
    @Mapping(source = "userId.id", target = "userId")
    @Mapping(source = "available.amount", target = "avaliableAmount")
    @Mapping(source = "dailyLimit.amount", target = "dailyLimitAmount")
    @Mapping(source = "dailyLimit.currency.value", target = "currency")
    AccountDO toDO(EAccount account);

    // 如果字段没有不一致，一行注解都不需要
    @Mapping(target = "accountNumber.value", source = "accountNumber")
    @Mapping(target = "accountId.value", source = "accountId")
    @Mapping(target = "userId.id", source = "userId")
    @Mapping(target = "available.amount", source = "avaliableAmount")
    @Mapping(target = "available.currency.value", source = "currency")
    @Mapping(target = "dailyLimit.amount", source = "dailyLimitAmount")
    @Mapping(target = "dailyLimit.currency.value", source = "currency")
    @Mapping(target = "id.value", source = "id")
    EAccount toEntity(AccountDO accountDO);
}