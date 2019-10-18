package photozs.rt.test.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import static spark.Spark.*;

/**
 * Account Service
 */
@Singleton
public class AccountService {

    private static final String VERSION = "v1.0";
    private static final String PREFIX = "/rt/" + VERSION;

    private final AccountController controller;

    @Inject
    public AccountService(AccountController controller) {
        this.controller = controller;
    }

    public void register() {
        get(PREFIX + "/accounts/" + Parameters.ACCOUNT_ID, controller::getAccountById);
    }

}
