package de.y3om11.safeshare.domain;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import de.y3om11.safeshare.domain.objects.filetransfer.FileTransfer;

public interface IVisitor {

    boolean visit(FileTransfer fileTransfer);
    boolean visit(DnsEntry dnsEntry);

}
