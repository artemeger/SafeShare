package de.y3om11.safeshare.persistence.adapter.persistence.appstate;

import de.y3om11.safeshare.domain.objects.appstate.AppState;
import de.y3om11.safeshare.domain.objects.appstate.AppStateBuilder;
import jetbrains.exodus.entitystore.Entity;
import jetbrains.exodus.entitystore.StoreTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppStateMapper {

    @Autowired
    private AppStateBuilder appStateBuilder;

    public AppState getOrDefault(final StoreTransaction tx){
        return Optional.ofNullable
                (tx.getAll(AppState.name).getFirst())
                .map(state -> appStateBuilder
                        .withHash((String) state.getProperty(AppState.field_hash))
                        .withHeight((Long) state.getProperty(AppState.field_height))
                        .build())
                .orElse(appStateBuilder
                        .withHash("")
                        .withHeight(0L)
                        .build());
    }

    public void update(final StoreTransaction tx, final String hash, final Long height){
        Optional.ofNullable(tx.getAll(AppState.name).getFirst())
                .ifPresent(state -> {
                    state.setProperty(AppState.field_hash, hash);
                    state.setProperty(AppState.field_height, height);
                });
    }

    public void create(final StoreTransaction tx, final AppState appState){
        final boolean isPresent = Optional.ofNullable(tx.getAll(AppState.name).getFirst()).isPresent();
        if (isPresent) {
            final Entity state = tx.newEntity(AppState.name);
            state.setProperty(AppState.field_hash, appState.hash);
            state.setProperty(AppState.field_height, appState.height);
        }
    }
}
