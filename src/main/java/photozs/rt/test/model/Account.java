package photozs.rt.test.model;


import java.math.BigDecimal;

public class Account {

    private String accountId;
    private String owner;
    private BigDecimal amount;

    public Account() {
    }

    public Account(String accountId, String owner, BigDecimal amount) {
        this.accountId = accountId;
        this.owner = owner;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
