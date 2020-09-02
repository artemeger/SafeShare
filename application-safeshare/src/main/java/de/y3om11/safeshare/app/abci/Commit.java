package de.y3om11.safeshare.app.abci;

import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.types.RequestCommit;
import com.github.jtendermint.jabci.types.ResponseCommit;
import de.y3om11.safeshare.domain.gateway.repository.IAppStateRepository;
import de.y3om11.safeshare.domain.objects.appstate.AppState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Commit implements ICommit {

    private final static Logger log = LoggerFactory.getLogger(Commit.class);

    @Autowired
    private IAppStateRepository appStateRepository;

    @Override
    public ResponseCommit requestCommit(final RequestCommit requestCommit) {
        final AppState currentAppState = appStateRepository.getAppState();
        log.info("Current Appstate : " + currentAppState.hash + " " + currentAppState.height);
        appStateRepository.increment(appStateRepository.getAppState());
        final AppState appStateNew = appStateRepository.getAppState();
        log.info("Current Appstate : " + appStateNew.hash + " " + appStateNew.height);
        return ResponseCommit.newBuilder()
                .setData(appStateNew.getAsByteString())
                .build();
    }
}
