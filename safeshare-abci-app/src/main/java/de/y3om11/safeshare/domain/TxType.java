package de.y3om11.safeshare.domain;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;
import de.y3om11.safeshare.domain.objects.filetransfer.FileTransfer;

import java.util.*;
import java.util.stream.Stream;

public enum TxType {

    FILE_TRANSFER(1L, FileTransfer.class),
    DNS_ENTRY(2L, DnsEntry.class);

    public final long identifier;
    public final Class<? extends IDomainTx> clazz;

    TxType(final long identifier, final Class<? extends IDomainTx> clazz) {
        this.identifier = identifier;
        this.clazz = clazz;
    }

    public static TxType fromIdentifier(final long identifier){
        return Stream.of(values())
                .filter(txType -> txType.identifier == identifier)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
