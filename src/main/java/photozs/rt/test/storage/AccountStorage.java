package photozs.rt.test.storage;

import photozs.rt.test.model.Account;
import photozs.rt.test.model.AccountException;

import java.util.Optional;

public interface AccountStorage {
    Optional<Account> get(String accountId);

    void create(Account account) throws AccountException;

    Account delete(String accountId) throws AccountException;
}
