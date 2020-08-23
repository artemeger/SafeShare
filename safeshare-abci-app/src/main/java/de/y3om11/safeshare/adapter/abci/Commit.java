package de.y3om11.safeshare.adapter.abci;

import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.types.RequestCommit;
import com.github.jtendermint.jabci.types.ResponseCommit;
import de.y3om11.safeshare.adapter.persistence.appstate.AppStateRepository;
import de.y3om11.safeshare.domain.objects.appstate.AppState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Commit implements ICommit {

    @Autowired
    private AppStateRepository appStateRepository;

    @Override
    public ResponseCommit requestCommit(final RequestCommit requestCommit) {
        appStateRepository.increment(appStateRepository.getAppState());
        final AppState appState = appStateRepository.getAppState();
        return ResponseCommit.newBuilder()
                .setData(appState.getAsByteString())
                .build();
    }
}
