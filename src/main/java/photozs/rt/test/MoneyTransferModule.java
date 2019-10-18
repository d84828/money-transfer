package photozs.rt.test;

import com.google.inject.AbstractModule;
import photozs.rt.test.storage.AccountStorage;
import photozs.rt.test.storage.ImMemoryAccountStorage;

public class MoneyTransferModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountStorage.class).to(ImMemoryAccountStorage.class).asEagerSingleton();
    }
}
