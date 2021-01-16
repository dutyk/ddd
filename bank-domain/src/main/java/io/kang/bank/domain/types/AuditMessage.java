package io.kang.bank.domain.types;

import com.alibaba.fastjson.JSON;
import io.kang.bank.domain.entity.Account;
import io.kang.bank.types.Money;
import io.kang.bank.types.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class AuditMessage {
    private UserId userId;
    private Account source;
    private Account target;
    private Money money;
    private Date date;

    public AuditMessage(Account source, Account target, Money money) {
        this.source = source;
        this.target = target;
        this.money = money;
    }

    public String serialize(AuditMessage auditMessage) {
        return JSON.toJSONString(auditMessage);
    }

    public static AuditMessage deserialize(String value) {
        return (AuditMessage) JSON.parse(value);
    }
}