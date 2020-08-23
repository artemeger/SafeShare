package de.y3om11.safeshare.domain.objects.filetransfer;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public class FileTransferBuilder {

    String transferId;
    String from;
    String to;
    String fileReference;
    byte [] secretKey;
    Long timestamp;

    public FileTransferBuilder withTransferId(final String transferId){
        this.transferId = transferId;
        return this;
    }

    public FileTransferBuilder withFrom(final String from){
        this.from = from;
        return this;
    }

    public FileTransferBuilder withTo(final String to){
        this.to = to;
        return this;
    }

    public FileTransferBuilder withFileReference(final String fileReference){
        this.fileReference = fileReference;
        return this;
    }

    public FileTransferBuilder withSecretKey(final byte[] secretKey){
        this.secretKey = secretKey;
        return this;
    }

    public FileTransferBuilder withTimestamp(final Long timestamp){
        this.timestamp = timestamp;
        return this;
    }

    public FileTransfer build(){
        return new FileTransfer(this);
    }
}
