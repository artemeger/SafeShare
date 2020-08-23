package de.y3om11.safeshare.adapter.abci;

import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.ResponseDeliverTx;
import de.y3om11.safeshare.app.visitors.CheckTxValidationVisitor;
import de.y3om11.safeshare.domain.IDomainTx;
import de.y3om11.safeshare.domain.serialization.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class DeliverTx implements IDeliverTx {

    @Autowired
    private CheckTxValidationVisitor validator;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResponseDeliverTx receivedDeliverTx(final RequestDeliverTx requestDeliverTx) {
        final AtomicBoolean result = new AtomicBoolean(false);
        final String txHexString = new String(requestDeliverTx.getTx().toByteArray());
        final Optional<IDomainTx> txOpt = transactionMapper.getTxFromHexString(txHexString);
        txOpt.ifPresent(tx -> result.set(tx.visit(validator)));
        return ResponseDeliverTx.newBuilder()
                .setCode(result.get() ? 1 : 0)
                .build();
    }

}
