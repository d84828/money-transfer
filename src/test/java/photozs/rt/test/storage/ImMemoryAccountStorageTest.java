package photozs.rt.test.storage;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import photozs.rt.test.model.Account;
import photozs.rt.test.model.AccountException;

import java.math.BigDecimal;

public class ImMemoryAccountStorageTest {

    private AccountStorage storage;

    @BeforeMethod
    public void setUp() {
        storage = new ImMemoryAccountStorage();
    }

    @Test
    public void createsAccount() throws AccountException {
        Account account = new Account("1", "Sergey", BigDecimal.valueOf(1500.75));
        storage.create(account);
        assertSame(storage.get("1").orElse(null), account);
    }

    @Test(expectedExceptions = AccountException.class, expectedExceptionsMessageRegExp = "Account already exists")
    public void throwsExceptionWhenAlreadyExists() throws AccountException {
        Account account = new Account("1", "Sergey", BigDecimal.valueOf(1500.75));
        try {
            storage.create(account);
        } catch (AccountException ignore) {
            //ignoring first
        }
        //check account exists
        assertTrue(storage.get("1").isPresent());
        assertSame(storage.get("1").orElse(null), account);
        //adding duplicate accountId
        Account duplicate = new Account("1", "John", BigDecimal.valueOf(5000));
        storage.create(duplicate);
    }

    @Test
    public void deletesAccount() throws AccountException {
        Account account1 = new Account("1", "Sergey", BigDecimal.valueOf(1500.75));
        storage.create(account1);
        Account account2 = new Account("2", "Sergey", BigDecimal.valueOf(0));
        storage.create(account2);

        //check account exists
        assertSame(storage.get("1").orElse(null), account1);

        Account deleted = storage.delete("1");

        assertSame(deleted, account1);
        assertFalse(storage.get("1").isPresent());
    }

    @Test(expectedExceptions = AccountException.class, expectedExceptionsMessageRegExp = "Unknown account")
    public void throwsExceptionWhenDeletesUnknownAccount() throws AccountException {
        Account account1 = new Account("1", "Sergey", BigDecimal.valueOf(1500.75));
        storage.create(account1);

        storage.delete("unknown");

    }
}
