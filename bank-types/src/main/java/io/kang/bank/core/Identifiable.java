package io.kang.bank.core;

import java.io.Serializable;

public interface Identifiable<ID extends Identifier> extends Serializable {
    ID getId();
}