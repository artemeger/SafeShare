package de.y3om11.safeshare.app.visitors;

import de.y3om11.safeshare.domain.gateway.logic.delivertx.IDnsEntryDeliverTx;
import de.y3om11.safeshare.domain.gateway.visitor.IVisitor;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import de.y3om11.safeshare.domain.objects.filetransfer.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliverTxExecutionVisitor implements IVisitor {

    @Autowired
    private IDnsEntryDeliverTx dnsEntryDeliverTx;

    @Override
    public boolean visit(FileTransfer fileTransfer) {
        return false;
    }

    @Override
    public boolean visit(DnsEntry dnsEntry) {
        return dnsEntryDeliverTx.execute(dnsEntry);
    }
}
