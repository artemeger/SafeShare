package de.y3om11.safeshare.app;

import com.github.jtendermint.jabci.api.*;
import com.github.jtendermint.jabci.socket.TSocket;
import com.github.jtendermint.jabci.types.*;
import de.y3om11.safeshare.adapter.abci.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFacade implements IBeginBlock, ICheckTx, ICommit, IInfo, IDeliverTx, IQuery, IEndBlock {

    private final static Logger log = LoggerFactory.getLogger(Info.class);

    @Autowired
    private BeginBlock beginBlock;

    @Autowired
    private CheckTx checkTx;

    @Autowired
    private Commit commit;

    @Autowired
    private DeliverTx deliverTx;

    @Autowired
    private EndBlock endBlock;

    @Autowired
    private Info info;

    @Autowired
    private Query query;

    public void startSocket(){
        log.info("Starting Socket on 26658");
        TSocket socket = new TSocket((exception, connection, event) -> { },
                (name, count) -> { },
                (name, remaining) -> { });
        socket.registerListener(this);
        Thread t = new Thread(socket::start);
        t.setName("ABCI Socket Thread startup");
        t.setDaemon(true);
        t.start();
        log.info("Socket running on 26658");
    }

    @Override
    public ResponseBeginBlock requestBeginBlock(RequestBeginBlock requestBeginBlock) {
        return beginBlock.requestBeginBlock(requestBeginBlock);
    }

    @Override
    public ResponseCheckTx requestCheckTx(RequestCheckTx requestCheckTx) {
        return checkTx.requestCheckTx(requestCheckTx);
    }

    @Override
    public ResponseCommit requestCommit(RequestCommit requestCommit) {
        return commit.requestCommit(requestCommit);
    }

    @Override
    public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx requestDeliverTx) {
        return deliverTx.receivedDeliverTx(requestDeliverTx);
    }

    @Override
    public ResponseEndBlock requestEndBlock(RequestEndBlock requestEndBlock) {
        return endBlock.requestEndBlock(requestEndBlock);
    }

    @Override
    public ResponseInfo requestInfo(RequestInfo requestInfo) {
        return info.requestInfo(requestInfo);
    }

    @Override
    public ResponseQuery requestQuery(RequestQuery requestQuery) {
        return query.requestQuery(requestQuery);
    }
}
