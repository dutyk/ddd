package io.kang.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Currency implements Serializable {
    private String value;

    @Override
    public boolean equals(Object obj) {
        Currency currency = (Currency) obj;
        return value.equals(currency.getValue());
    }
}