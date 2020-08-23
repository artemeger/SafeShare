package de.y3om11.safeshare.domain;

import java.util.Map;

public interface IDomainTx {

    Map<String, String> getTags();
    TxType getType();
    boolean visit(IVisitor validator);
}
