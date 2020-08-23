package de.y3om11.safeshare.app.gateway;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;

import java.util.Optional;

public interface IDnsEntryRepository {

    Optional<DnsEntry> findDnsEntryById(String dnsEntryId);
    void createDnsEntry(DnsEntry dnsEntry);
    void updateDnsEntry(DnsEntry dnsEntry);
    Optional<byte[]> findPublicKeyBytesByDomainName(String domainName);

}
