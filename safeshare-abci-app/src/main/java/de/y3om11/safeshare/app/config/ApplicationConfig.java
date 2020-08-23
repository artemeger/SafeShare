package de.y3om11.safeshare.app.config;

import de.y3om11.safeshare.adapter.abci.*;
import de.y3om11.safeshare.adapter.persistence.appstate.AppStateMapper;
import de.y3om11.safeshare.adapter.persistence.appstate.AppStateRepository;
import de.y3om11.safeshare.adapter.persistence.dnsentry.DnsEntryMapper;
import de.y3om11.safeshare.adapter.persistence.dnsentry.DnsEntryRepository;
import de.y3om11.safeshare.app.gateway.IAppStateRepository;
import de.y3om11.safeshare.app.gateway.IDnsEntryRepository;
import de.y3om11.safeshare.domain.objects.appstate.AppStateBuilder;
import de.y3om11.safeshare.domain.serialization.TransactionMapper;
import jetbrains.exodus.entitystore.PersistentEntityStoreImpl;
import jetbrains.exodus.entitystore.PersistentEntityStores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {

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

    @Bean
    public IAppStateRepository getAppStateRepository(){
        return new AppStateRepository();
    }

    @Bean
    public AppStateMapper getAppStateMapper(){
        return new AppStateMapper();
    }

    @Bean
    public BeginBlock getBeginBlock(){
        return new BeginBlock();
    }

    @Bean
    public CheckTx getCheckTx(){
        return new CheckTx();
    }

    @Bean
    public Commit getCommit(){
        return new Commit();
    }

    @Bean
    public DeliverTx getDeliverTx(){
        return new DeliverTx();
    }

    @Bean
    public EndBlock getEndBlock(){
        return new EndBlock();
    }

    @Bean
    public Info getInfo(){
        return new Info();
    }

    @Bean
    public Query getQuery(){
        return new Query();
    }
}
