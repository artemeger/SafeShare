package de.y3om11.safeshare.app;

import com.github.jtendermint.jabci.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFacade {

    @Autowired
    private IBeginBlock beginBlock;

    @Autowired
    private ICheckTx checkTx;

    @Autowired
    private ICommit commit;

    @Autowired
    private IDeliverTx deliverTx;

    @Autowired
    private IEndBlock endBlock;

    @Autowired
    private IInfo info;

    @Autowired
    private IQuery query;

}
