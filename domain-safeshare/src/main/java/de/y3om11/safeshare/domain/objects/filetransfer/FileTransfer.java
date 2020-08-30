package de.y3om11.safeshare.domain.objects.filetransfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.y3om11.safeshare.domain.gateway.tx.IDomainTx;
import de.y3om11.safeshare.domain.gateway.tx.TxType;
import de.y3om11.safeshare.domain.gateway.visitor.IVisitor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonDeserialize(builder = FileTransferBuilder.class)
public class FileTransfer implements IDomainTx {

    public final String transferId;
    public final String from;
    public final String to;
    public final String fileReference;
    public final byte [] secretKey;
    public final Long timestamp;

    public FileTransfer(final FileTransferBuilder builder){
        this.transferId = builder.transferId;
        this.from = builder.from;
        this.to = builder.to;
        this.fileReference = builder.fileReference;
        this.secretKey = builder.secretKey;
        this.timestamp = builder.timestamp;
    }

    @Override
    @JsonIgnore
    public Map<String, String> getTags() {
        final Map<String, String> tags = new HashMap<>();
        tags.put("transferId", transferId);
        tags.put("from", from);
        tags.put("to", to);
        tags.put("timestamp", String.valueOf(timestamp));
        return tags;
    }

    @Override
    @JsonIgnore
    public TxType getType() {
        return TxType.FILE_TRANSFER;
    }

    @Override
    @JsonIgnore
    public boolean visit(final IVisitor validator) {
        return validator.visit(this);
    }

    @Override
    @JsonIgnore
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileTransfer that = (FileTransfer) o;
        return transferId.equals(that.transferId) &&
                from.equals(that.from) &&
                to.equals(that.to) &&
                fileReference.equals(that.fileReference) &&
                Arrays.equals(secretKey, that.secretKey) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        int result = Objects.hash(transferId, from, to, fileReference, timestamp);
        result = 31 * result + Arrays.hashCode(secretKey);
        return result;
    }
}
