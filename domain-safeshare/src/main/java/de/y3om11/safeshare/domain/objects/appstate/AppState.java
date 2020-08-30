package de.y3om11.safeshare.domain.objects.appstate;

import com.google.protobuf.ByteString;

public class AppState {

    public final static String name = "appState";
    public final static String field_hash = "hash";
    public final static String field_height = "height";
    public final static String version = "1.0";
    public final String hash;
    public final Long height;

    public AppState(final AppStateBuilder builder){
        this.hash = builder.hash;
        this.height = builder.height;
    }

    public ByteString getAsByteString(){
        return ByteString.copyFromUtf8(hash + height);
    }
}
