package io.kang.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ItemId implements Serializable {
    private String id;
}