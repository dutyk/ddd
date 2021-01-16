package io.kang.bank.types.core;

import io.kang.bank.core.Identifier;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregateId implements Identifier {
    private Long value;
}