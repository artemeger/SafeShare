package de.y3om11.safeshare.domain.gateway.repository;

import de.y3om11.safeshare.domain.objects.appstate.AppState;

public interface IAppStateRepository {

    void increment(AppState appState);
    AppState getAppState();
}
