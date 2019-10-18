package photozs.rt.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import photozs.rt.test.model.Account;
import photozs.rt.test.model.AccountException;
import photozs.rt.test.service.AccountService;
import photozs.rt.test.storage.AccountStorage;
import photozs.rt.test.storage.ImMemoryAccountStorage;
import spark.Spark;

import java.math.BigDecimal;

/**
 * The main application class
 */
public class App  {

    private static Logger log = Logger.getLogger(App.class);
    private static Injector injector;

    public static void main(String[] args) {
        App app = new App();
        boolean res = app.init();

    }

    private boolean init() {
        log.info("Starting...");

        injector = Guice.createInjector(new MoneyTransferModule());

        AccountStorage storage = injector.getInstance(AccountStorage.class);

        try {
            storage.create(new Account("12345", "Sergey", BigDecimal.valueOf(120000)));
            storage.create(new Account("67890", "Anna", BigDecimal.valueOf(440000)));
        } catch (AccountException e) {
            log.error("Failed to start application. " + e.getLocalizedMessage(), e);
            return false;
        }

        AccountService accountService = injector.getInstance(AccountService.class);
        accountService.register();
        return true;
    }

}
