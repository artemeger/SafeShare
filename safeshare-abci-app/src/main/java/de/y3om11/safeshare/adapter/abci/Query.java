package de.y3om11.safeshare.adapter.abci;

import com.github.jtendermint.jabci.api.IQuery;
import com.github.jtendermint.jabci.types.RequestQuery;
import com.github.jtendermint.jabci.types.ResponseQuery;
import org.springframework.stereotype.Component;

@Component
public class Query implements IQuery {
    @Override
    public ResponseQuery requestQuery(RequestQuery requestQuery) {
        return ResponseQuery.newBuilder().build();
    }
}
