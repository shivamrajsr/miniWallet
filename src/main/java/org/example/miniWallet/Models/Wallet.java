package org.example.miniWallet.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

public class Wallet {
    private double balance;
    private final List<Transaction> transactionHistory;
    private final String userId;

    public Wallet(String userId) {
        this.userId = userId;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean deductMoney(double amount){
        balance -= amount;
        return true;
    }

    public boolean sendMoney(double amount, Wallet receiverWallet) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            receiverWallet.receiveMoney(amount);
            return true;
        }
        return false;
    }

    public boolean receiveMoney(double amount) {
        balance += amount;
        return true;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public List<Transaction> getFilteredTransactions(List<Transaction> transactions,String filterUserId) {
        return transactions.stream()
                .filter(t -> t.getReceiverId().equals(filterUserId))
                .collect(Collectors.toList());
    }

    public List<Transaction> getSortedTransactions(List<Transaction> transactions,Comparator<Transaction> sorter) {
        return transactions.stream()
                .sorted(sorter)
                .collect(Collectors.toList());
    }
    public String getUserId() {
        return userId;
    }
}