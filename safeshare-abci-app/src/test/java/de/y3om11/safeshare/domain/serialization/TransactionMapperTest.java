package de.y3om11.safeshare.domain.serialization;

import de.y3om11.safeshare.domain.objects.filetransfer.FileTransfer;
import de.y3om11.safeshare.domain.objects.filetransfer.FileTransferBuilder;
import org.junit.jupiter.api.Test;

class TransactionMapperTest {

    @Test
    void test(){
        FileTransfer f = new FileTransferBuilder()
                .withTransferId("id")
                .withFrom("from")
                .withTo("to")
                .withTimestamp(1L)
                .build();
        TransactionMapper transactionMapper = new TransactionMapper();
        String txString = transactionMapper.createTx(f).get();
        FileTransfer f2 = (FileTransfer) transactionMapper.getTxFromHexString(txString).get();
    }
}