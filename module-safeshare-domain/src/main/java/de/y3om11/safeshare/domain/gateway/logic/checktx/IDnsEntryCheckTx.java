package de.y3om11.safeshare.domain.gateway.logic.checktx;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;

@FunctionalInterface
public interface IDnsEntryCheckTx {

    boolean validate(DnsEntry dnsEntry);

}
