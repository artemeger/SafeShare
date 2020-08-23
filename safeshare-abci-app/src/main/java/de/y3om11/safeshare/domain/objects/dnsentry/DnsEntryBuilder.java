package de.y3om11.safeshare.domain.objects.dnsentry;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public class DnsEntryBuilder {

    String dnsEntryId;
    byte [] publicKeyBytes;
    String domainName;

    public DnsEntryBuilder withDnsEntry(final String dnsEntryId){
        this.dnsEntryId = dnsEntryId;
        return this;
    }

    public DnsEntryBuilder withPublicKeyBytes(final byte [] publicKeyBytes){
        this.publicKeyBytes = publicKeyBytes;
        return this;
    }

    public DnsEntryBuilder withDomainName(final String domainName){
        this.domainName = domainName;
        return this;
    }

    public DnsEntry build(){
        return new DnsEntry(this);
    }
}
