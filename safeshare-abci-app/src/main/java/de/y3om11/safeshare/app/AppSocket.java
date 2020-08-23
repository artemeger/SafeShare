package de.y3om11.safeshare.app;

import com.github.jtendermint.jabci.socket.ConnectionListener;
import com.github.jtendermint.jabci.socket.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppSocket {

    @Autowired
    public AppSocket(final ConnectionListener listener) {
        final Logger log = LoggerFactory.getLogger(AppSocket.class);
        final Thread socketThread = new Thread(() -> {
            final TSocket socket = new TSocket((exception, connect, event) -> {}, listener, (name, remaining) -> {});
            socket.registerListener(this);
        });
        socketThread.setName("ABCI Socket Thread startup");
        socketThread.setDaemon(true);
        socketThread.start();
        log.info("Socket Thread started");
    }
}
