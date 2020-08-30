package de.y3om11.safeshare.adapter.dns.logic;

import de.y3om11.safeshare.domain.gateway.logic.delivertx.IDnsEntryDeliverTx;
import de.y3om11.safeshare.domain.gateway.repository.IDnsEntryRepository;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DnsEntryDeliverTxLogic implements IDnsEntryDeliverTx {

    @Autowired
    private IDnsEntryRepository dnsEntryRepository;

    @Override
    public boolean execute(DnsEntry dnsEntry) {
        dnsEntryRepository.createDnsEntry(dnsEntry);
        return true;
    }
}
