package de.y3om11.safeshare.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ABCIApp {

    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger(AppSocket.class);
        try {
            SpringApplication.run(ABCIApp.class, args);
            Thread.currentThread().join();
        } catch (InterruptedException e){
            log.warn("Application thread was interrupted");
        } finally {
            log.info("Shutting down");
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup(){}
}
