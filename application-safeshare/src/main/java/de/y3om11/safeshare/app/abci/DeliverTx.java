package de.y3om11.safeshare.app.abci;

import com.github.jtendermint.jabci.api.CodeType;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.ResponseDeliverTx;
import com.google.protobuf.ByteString;
import de.y3om11.safeshare.app.visitors.CheckTxValidationVisitor;
import de.y3om11.safeshare.app.visitors.DeliverTxExecutionVisitor;
import de.y3om11.safeshare.domain.gateway.tx.IDomainTx;
import de.y3om11.safeshare.domain.serialization.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class DeliverTx implements IDeliverTx {

    private final static Logger log = LoggerFactory.getLogger(DeliverTx.class);

    @Autowired
    private CheckTxValidationVisitor validator;

    @Autowired
    private DeliverTxExecutionVisitor executor;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResponseDeliverTx receivedDeliverTx(final RequestDeliverTx requestDeliverTx) {
        log.info("DeliverTx called with: " +  requestDeliverTx.getTx().toStringUtf8());
        final AtomicBoolean result = new AtomicBoolean(false);
        final String txHexString = new String(requestDeliverTx.getTx().toByteArray());
        final Optional<IDomainTx> txOpt = transactionMapper.getTxFromHexString(txHexString);
        txOpt.ifPresent(tx -> {
            if(tx.visit(validator)) result.set(tx.visit(executor));
        });
        return ResponseDeliverTx.newBuilder()
                .setCode(result.get() ? CodeType.OK : CodeType.BAD)
                .setInfo(result.get() ? "Valid" : "Invalid")
                .setData(result.get() ? requestDeliverTx.getTx() : ByteString.copyFromUtf8(""))
                .build();
    }

}
