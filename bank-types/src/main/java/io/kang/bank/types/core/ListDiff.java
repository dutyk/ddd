package io.kang.bank.types.core;

import lombok.Data;
import java.util.List;

@Data
public class ListDiff extends Diff {
    private List<Diff> diffs;
}