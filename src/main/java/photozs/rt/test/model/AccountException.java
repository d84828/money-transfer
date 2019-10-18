package photozs.rt.test.model;

public class AccountException extends Exception {

    private final String accountId;
    private AccountExceptionCode code;

    public AccountException(String accountId, AccountExceptionCode code, String message) {
        super(message);
        this.accountId = accountId;
        this.code = code;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountExceptionCode getCode() {
        return code;
    }
}
