package photozs.rt.test.storage;

import com.google.common.collect.Maps;
import photozs.rt.test.model.Account;
import photozs.rt.test.model.AccountException;
import photozs.rt.test.model.AccountExceptionCode;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

/**
 * In-memory account data storage
 */
public class ImMemoryAccountStorage implements AccountStorage {

    private final ConcurrentMap<String, Account> internalStorage = Maps.newConcurrentMap();

    @Override
    public Optional<Account> get(String accountId) {
        return Optional.ofNullable(internalStorage.get(accountId));
    }

    @Override
    public void create(Account account) throws AccountException {
        Account existing = internalStorage.putIfAbsent(account.getAccountId(), account);
        if(existing != null) {
            throw new AccountException(account.getAccountId(), AccountExceptionCode.AlreadyExists, "Account already exists");
        }
    }

    @Override
    public Account delete(String accountId) throws AccountException {
        Account removed = internalStorage.remove(accountId);
        if(removed == null) {
            throw new AccountException(accountId, AccountExceptionCode.Unknown, "Unknown account");
        }
        return removed;
    }
}
