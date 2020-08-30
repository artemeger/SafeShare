package de.y3om11.safeshare.domain.serialization;

import de.y3om11.safeshare.domain.gateway.tx.IDomainTx;
import de.y3om11.safeshare.domain.gateway.tx.TxType;
import jetbrains.exodus.util.HexUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Optional;

public class TransactionMapper {

    private final JacksonSerializer jacksonSerializer = new JacksonSerializer();

    public Optional<String> createTx(final IDomainTx tx){
        try {
            final StringBuilder sb = new StringBuilder();
            final byte[] txTypeBytes = ByteBuffer.allocate(8).putLong(tx.getType().identifier).array();
            sb.append(HexUtil.byteArrayToString(txTypeBytes));
            final String txString = HexUtil.byteArrayToString(jacksonSerializer.getBytesFromObject(tx));
            final byte[] txStringLength = ByteBuffer.allocate(4).putInt(txString.length()).array();
            sb.append(HexUtil.byteArrayToString(txStringLength));
            sb.append(txString);
            return Optional.of(sb.toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Optional<IDomainTx> getTxFromHexString(final String hexString){
        try {
            final String txTypeString = hexString.substring(0, 16);
            final String txSizeString = hexString.substring(16, 24);
            final TxType txType = TxType.fromIdentifier(ByteBuffer.wrap(HexUtil.stringToByteArray(txTypeString)).getLong());
            final int txSize = ByteBuffer.wrap(HexUtil.stringToByteArray(txSizeString)).getInt();
            final String txValue = hexString.substring(24, 24 + txSize);
            return Optional.of(jacksonSerializer.getObjectFromBytes(txType.clazz, HexUtil.stringToByteArray(txValue)));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
