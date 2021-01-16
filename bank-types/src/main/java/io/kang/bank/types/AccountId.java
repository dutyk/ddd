package io.kang.bank.types;

import io.kang.bank.core.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class AccountId implements Identifier {
    private String value;
}