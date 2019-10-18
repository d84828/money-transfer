package photozs.rt.test.service;


import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import photozs.rt.test.model.Account;
import photozs.rt.test.storage.AccountStorage;
import spark.Request;
import spark.Response;

import java.util.Optional;

@Singleton
public class AccountController {

    private AccountStorage storage;

    @Inject
    public AccountController(AccountStorage storage) {
        this.storage = storage;
    }

    String getAccountById(Request req, Response res) {
        res.type("application/json");
        Gson gson = new Gson();
        String accountId = req.params(Parameters.ACCOUNT_ID);
        Optional<Account> account = storage.get(accountId);
        if (account.isPresent()) {
            res.status(200);
            return gson.toJson(account);
        }
        res.status(404);
        return gson.toJson(new ErrorResponse(404, "Not found"));

    }
}
