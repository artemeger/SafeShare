package de.y3om11.safeshare.app.config;

import com.github.jtendermint.jabci.socket.ConnectionListener;
import de.y3om11.safeshare.adapter.persistence.dnsentry.DnsEntryMapper;
import de.y3om11.safeshare.adapter.persistence.dnsentry.DnsEntryRepository;
import de.y3om11.safeshare.app.gateway.IDnsEntryRepository;
import de.y3om11.safeshare.domain.objects.appstate.AppStateBuilder;
import de.y3om11.safeshare.domain.serialization.TransactionMapper;
import jetbrains.exodus.entitystore.PersistentEntityStoreImpl;
import jetbrains.exodus.entitystore.PersistentEntityStores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {

    private final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public ConnectionListener getDefaultConnectionListener(){
        return (name, count) -> {
            if(count == 3) log.info("AppSocket fully connected");
        };
    }

    @Bean
    public PersistentEntityStoreImpl getDatabase(){
        return PersistentEntityStores.newInstance(".appData");
    }

    @Bean
    @Scope("prototype")
    public AppStateBuilder getAppStateBuilder(){
        return new AppStateBuilder();
    }

    @Bean
    public TransactionMapper getTransactionMapper(){
        return new TransactionMapper();
    }

    @Bean
    public IDnsEntryRepository getDnsEntryRepository(){
        return new DnsEntryRepository();
    }

    @Bean
    public DnsEntryMapper getDnsEntryMapper(){
        return new DnsEntryMapper();
    }
}
