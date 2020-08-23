package de.y3om11.safeshare.adapter.abci;

import com.github.jtendermint.jabci.api.ICheckTx;
import com.github.jtendermint.jabci.types.RequestCheckTx;
import com.github.jtendermint.jabci.types.ResponseCheckTx;
import de.y3om11.safeshare.app.visitors.CheckTxValidationVisitor;
import de.y3om11.safeshare.domain.IDomainTx;
import de.y3om11.safeshare.domain.serialization.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CheckTx implements ICheckTx {

    private final static Logger log = LoggerFactory.getLogger(CheckTx.class);

    @Autowired
    private CheckTxValidationVisitor validator;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResponseCheckTx requestCheckTx(final RequestCheckTx requestCheckTx) {
        log.info("CheckTx called with: " +  requestCheckTx.getTx().toString());
        final AtomicBoolean result = new AtomicBoolean(false);
        final String txHexString = new String(requestCheckTx.getTx().toByteArray());
        final Optional<IDomainTx> txOpt = transactionMapper.getTxFromHexString(txHexString);
        txOpt.ifPresent(tx -> result.set(tx.visit(validator)));
        return ResponseCheckTx.newBuilder()
                .setCode(result.get() ? 1 : 0)
                .build();
    }
}
