package de.y3om11.safeshare.app.visitors;

import de.y3om11.safeshare.app.logic.IDnsEntryCheckTx;
import de.y3om11.safeshare.domain.IVisitor;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import de.y3om11.safeshare.domain.objects.filetransfer.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckTxValidationVisitor implements IVisitor {

    @Autowired
    private IDnsEntryCheckTx checkTxLogic;

    @Override
    public boolean visit(final FileTransfer fileTransfer) {
        return true;
    }

    @Override
    public boolean visit(final DnsEntry dnsEntry) {
        return checkTxLogic.validate(dnsEntry);
    }

}
