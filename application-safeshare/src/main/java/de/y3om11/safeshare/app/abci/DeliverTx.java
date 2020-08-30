package de.y3om11.safeshare.app.abci;

import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.ResponseDeliverTx;
import de.y3om11.safeshare.app.visitors.CheckTxValidationVisitor;
import de.y3om11.safeshare.app.visitors.DeliverTxExecutionVisitor;
import de.y3om11.safeshare.domain.gateway.tx.IDomainTx;
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
    private DeliverTxExecutionVisitor executor;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResponseDeliverTx receivedDeliverTx(final RequestDeliverTx requestDeliverTx) {
        final AtomicBoolean result = new AtomicBoolean(false);
        final String txHexString = new String(requestDeliverTx.getTx().toByteArray());
        final Optional<IDomainTx> txOpt = transactionMapper.getTxFromHexString(txHexString);
        txOpt.ifPresent(tx -> {
            if(tx.visit(validator)) result.set(tx.visit(executor));
        });
        return ResponseDeliverTx.newBuilder()
                .setCode(result.get() ? 1 : 0)
                .build();
    }

}
