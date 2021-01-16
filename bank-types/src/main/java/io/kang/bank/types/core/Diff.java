package io.kang.bank.types.core;

import lombok.Data;

@Data
public class Diff {

    private DiffType type;
    private Object oldValue;
    private Object newValue;
}