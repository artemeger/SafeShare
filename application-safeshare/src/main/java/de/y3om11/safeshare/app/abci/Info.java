package de.y3om11.safeshare.app.abci;

import com.github.jtendermint.jabci.api.IInfo;
import com.github.jtendermint.jabci.types.RequestInfo;
import com.github.jtendermint.jabci.types.ResponseInfo;
import com.google.protobuf.ByteString;
import de.y3om11.safeshare.domain.gateway.repository.IAppStateRepository;
import de.y3om11.safeshare.domain.objects.appstate.AppState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class Info implements IInfo {

    private final static Logger log = LoggerFactory.getLogger(Info.class);

    @Autowired
    private IAppStateRepository appStateRepository;

    @Override
    public ResponseInfo requestInfo(final RequestInfo requestInfo) {
        final AppState appState = appStateRepository.getAppState();
        log.info(String.format("AppSate at height %s with hash %s", appState.height, appState.hash));
        return ResponseInfo.newBuilder()
                .setLastBlockAppHash(ByteString.copyFrom(appState.hash, StandardCharsets.UTF_8))
                .setLastBlockHeight(appState.height)
                .setVersion(AppState.version)
                .build();
    }

}
