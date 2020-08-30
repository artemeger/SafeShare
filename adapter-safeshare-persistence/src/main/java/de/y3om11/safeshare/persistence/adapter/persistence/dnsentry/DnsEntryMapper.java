package de.y3om11.safeshare.persistence.adapter.persistence.dnsentry;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntryBuilder;
import jetbrains.exodus.entitystore.Entity;
import jetbrains.exodus.entitystore.StoreTransaction;
import jetbrains.exodus.util.HexUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Component
public class DnsEntryMapper {

    public static final String name = "dnsEntry";
    public static final String field_dnsEntryId = "dnsEntryId";
    public static final String field_publicKeyBytes = "publicKeyBytes";
    public static final String field_domainName = "domainName";

    public void create(final StoreTransaction tx, final DnsEntry dnsEntry){
        final boolean isPresent = Optional.ofNullable(tx.find(name, field_dnsEntryId, dnsEntry.dnsEntryId).getFirst()).isPresent();
        if(!isPresent){
            final Entity newDnsEntry = tx.newEntity(name);
            newDnsEntry.setProperty(field_dnsEntryId, dnsEntry.dnsEntryId);
            newDnsEntry.setProperty(field_publicKeyBytes, HexUtil.byteArrayToString(dnsEntry.publicKeyBytes));
            newDnsEntry.setProperty(field_domainName, dnsEntry.domainName);
        }
    }

    public void update(final StoreTransaction tx, final DnsEntry dnsEntry){
        Optional.ofNullable(tx.find(name, field_dnsEntryId, dnsEntry.dnsEntryId).getFirst())
                .ifPresent(entry -> {
                    entry.setProperty(field_domainName, dnsEntry.domainName);
                    entry.setProperty(field_publicKeyBytes, HexUtil.byteArrayToString(dnsEntry.publicKeyBytes));
                });
    }

    public Optional<DnsEntry> findById(final StoreTransaction tx, final String dnsEntryId){
        return Optional.ofNullable(tx.find(name, field_dnsEntryId, dnsEntryId).getFirst())
                .map(entry -> new DnsEntryBuilder()
                        .withDnsEntryId((String) entry.getProperty(field_dnsEntryId))
                        .withDomainName((String) entry.getProperty(field_domainName))
                        .withPublicKeyBytes(HexUtil.stringToByteArray((String) requireNonNull(entry.getProperty(field_publicKeyBytes))))
                        .build());
    }

    public Optional<DnsEntry> findByDomainName(final StoreTransaction tx, final String domainName){
        return Optional.ofNullable(tx.find(name, field_domainName, domainName).getFirst())
                .map(entry -> new DnsEntryBuilder()
                        .withDnsEntryId((String) entry.getProperty(field_dnsEntryId))
                        .withDomainName((String) entry.getProperty(field_domainName))
                        .withPublicKeyBytes(HexUtil.stringToByteArray((String) requireNonNull(entry.getProperty(field_publicKeyBytes))))
                        .build());
    }
}
