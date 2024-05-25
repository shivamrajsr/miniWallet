package org.example.miniWallet.Models;

public class User {
    private final String userId;
    private final String name;
    private final Wallet wallet;

    private boolean firstMoreThan100TransactionMade;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.wallet = new Wallet(userId);
        this.firstMoreThan100TransactionMade = false;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean isFirstMoreThan100TransactionMade() {
        return firstMoreThan100TransactionMade;
    }

    public void setFirstMoreThan100TransactionMade(boolean firstMoreThan100TransactionMade) {
        this.firstMoreThan100TransactionMade = firstMoreThan100TransactionMade;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
