package de.y3om11.safeshare.domain.gateway.logic.delivertx;

import de.y3om11.safeshare.domain.objects.dnsentry.DnsEntry;

@FunctionalInterface
public interface IDnsEntryDeliverTx {

    boolean execute(DnsEntry dnsEntry);

}
