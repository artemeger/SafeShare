package de.y3om11.safeshare.domain.objects.dnsentry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.y3om11.safeshare.domain.IDomainTx;
import de.y3om11.safeshare.domain.IVisitor;
import de.y3om11.safeshare.domain.TxType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = DnsEntryBuilder.class)
public class DnsEntry implements IDomainTx {

    public final String dnsEntryId;
    public final byte [] publicKeyBytes;
    public final String domainName;

    public DnsEntry(final DnsEntryBuilder builder){
        this.dnsEntryId = requireNonNull(builder.dnsEntryId, "DnsEntryId must be not null");
        this.publicKeyBytes = requireNonNull(builder.publicKeyBytes, "PublicKeyBytes must be not null");
        this.domainName = requireNonNull(builder.domainName, "DomainName must be not null");
    }

    @JsonIgnore
    @Override
    public Map<String, String> getTags() {
        final Map<String, String> tags = new HashMap<>();
        tags.put("dnsEntryId", dnsEntryId);
        tags.put("domainName", domainName);
        return tags;
    }

    @JsonIgnore
    @Override
    public TxType getType() {
        return TxType.DNS_ENTRY;
    }

    @JsonIgnore
    @Override
    public boolean visit(IVisitor validator) {
        return validator.visit(this);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DnsEntry dnsEntry = (DnsEntry) o;
        return Arrays.equals(publicKeyBytes, dnsEntry.publicKeyBytes) &&
                domainName.equals(dnsEntry.domainName) &&
                dnsEntryId.equals(dnsEntry.dnsEntryId);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        int result = Objects.hash(domainName, dnsEntryId);
        result = 31 * result + Arrays.hashCode(publicKeyBytes);
        return result;
    }
}
