package de.y3om11.safeshare.adapter.abci;

import com.github.jtendermint.jabci.api.IEndBlock;
import com.github.jtendermint.jabci.types.RequestEndBlock;
import com.github.jtendermint.jabci.types.ResponseEndBlock;
import org.springframework.stereotype.Component;

@Component
public class EndBlock implements IEndBlock {
    @Override
    public ResponseEndBlock requestEndBlock(RequestEndBlock requestEndBlock) {
        return ResponseEndBlock.newBuilder().build();
    }
}
