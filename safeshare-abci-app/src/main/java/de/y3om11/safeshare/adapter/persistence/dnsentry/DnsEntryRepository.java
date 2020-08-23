package de.y3om11.safeshare.adapter.persistence.dnsentry;

import de.y3om11.safeshare.app.gateway.IDnsEntryRepository;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import jetbrains.exodus.entitystore.PersistentEntityStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DnsEntryRepository implements IDnsEntryRepository {

    @Autowired
    private PersistentEntityStoreImpl store;

    @Autowired
    private DnsEntryMapper dnsEntryMapper;

    @Override
    public Optional<DnsEntry> findDnsEntryById(final String dnsEntryId) {
        return store.computeInReadonlyTransaction(tx -> dnsEntryMapper.findById(tx, dnsEntryId));
    }

    @Override
    public void createDnsEntry(final DnsEntry dnsEntry) {
        store.computeInTransaction(tx -> {
            dnsEntryMapper.create(tx, dnsEntry);
            return true;
        });
    }

    @Override
    public void updateDnsEntry(final DnsEntry dnsEntry) {
        store.computeInTransaction(tx -> {
            dnsEntryMapper.update(tx, dnsEntry);
            return true;
        });
    }

    @Override
    public Optional<byte[]> findPublicKeyBytesByDomainName(final String domainName) {
        return store.computeInReadonlyTransaction(tx -> dnsEntryMapper.findByDomainName(tx, domainName)
                .map(dnsEntry -> dnsEntry.publicKeyBytes));
    }
}
