package de.y3om11.safeshare.domain.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.y3om11.safeshare.domain.IDomainTx;

import java.io.IOException;

class JacksonSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T extends IDomainTx> T getObjectFromBytes(final Class<T> objClass, final byte[] value) throws IOException {
        return mapper.readValue(value, objClass);
    }

    public byte[] getBytesFromObject(final IDomainTx msg) throws JsonProcessingException {
        return mapper.writer().writeValueAsBytes(msg);
    }
}
