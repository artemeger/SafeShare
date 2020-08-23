package de.y3om11.safeshare.domain.objects.appstate;

public class AppStateBuilder {

    String hash;
    Long height;

    public AppStateBuilder withHash(final String hash){
        this.hash = hash;
        return this;
    }

    public AppStateBuilder withHeight(final Long heigth){
        this.height = heigth;
        return this;
    }

    public AppState build(){
        return new AppState(this);
    }
}
