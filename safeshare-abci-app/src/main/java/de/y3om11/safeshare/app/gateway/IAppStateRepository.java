package de.y3om11.safeshare.app.gateway;

import de.y3om11.safeshare.domain.objects.appstate.AppState;

public interface IAppStateRepository {

    void increment(AppState appState);
    AppState getAppState();
}
