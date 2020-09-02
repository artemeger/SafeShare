package de.y3om11.safeshare.adapter.dns.logic;

import de.y3om11.safeshare.domain.gateway.logic.checktx.IDnsEntryCheckTx;
import de.y3om11.safeshare.domain.gateway.repository.IDnsEntryRepository;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DnsEntryCheckTxLogic implements IDnsEntryCheckTx {

    @Autowired
    private IDnsEntryRepository dnsEntryRepository;

    @Override
    public boolean validate(final DnsEntry dnsEntry) {
        return dnsEntryRepository.findDnsEntryById(dnsEntry.dnsEntryId).isEmpty() &&
                dnsEntryRepository.findPublicKeyBytesByDomainName(dnsEntry.domainName).isEmpty();
    }
}
