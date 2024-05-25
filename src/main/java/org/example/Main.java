package org.example;

import org.example.miniWallet.Models.TopUpMode;
import org.example.miniWallet.Models.Transaction;
import org.example.miniWallet.Service.MiniWalletServiceImpl;
import org.example.miniWallet.Utils.Exceptions.NotSufficientBalanceException;
import org.example.miniWallet.Utils.Exceptions.TransactionNotSuccessfulException;
import org.example.miniWallet.Utils.Exceptions.UserAlreadyExistsException;
import org.example.miniWallet.Utils.Exceptions.UserNotFoundException;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NotSufficientBalanceException, UserAlreadyExistsException, UserNotFoundException, TransactionNotSuccessfulException {
        MiniWalletServiceImpl phonePeWallet = new MiniWalletServiceImpl();
        phonePeWallet.initializeMiniWallet();

        phonePeWallet.registerUser("user1", "Alice");
        //phonePeWallet.registerUser("user1", "Alice");
        phonePeWallet.registerUser("user2", "Bob");
        phonePeWallet.registerUser("user3", "Charles");

        phonePeWallet.topUpWallet("user1", 200, TopUpMode.CC);
        phonePeWallet.topUpWallet("user2", 150,TopUpMode.DC);
        phonePeWallet.topUpWallet("user3", 150,TopUpMode.UPI);

        System.out.println(STR."User1 Balance: \{phonePeWallet.fetchBalance("user1")}");
        System.out.println(STR."User2 Balance: \{phonePeWallet.fetchBalance("user2")}");

        phonePeWallet.sendMoney("user1", "user2", 25);
        phonePeWallet.sendMoney("user1", "user3", 25);



        List<Transaction> transactions = phonePeWallet.getTransactions("user1", "user3", Comparator.comparing(Transaction::getDate));
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        phonePeWallet.sendMoney("user2", "user1", 25);
        transactions = phonePeWallet.getTransactions("user1", null, Comparator.comparing(Transaction::getDate));
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
//        System.out.println(STR."User1 Balance after transaction: \{phonePeWallet.fetchBalance("user1")}");
//        System.out.println(STR."User2 Balance after transaction: \{phonePeWallet.fetchBalance("user2")}");
//        phonePeWallet.sendMoney("user2", "user1", 101);
//        transactions = phonePeWallet.getTransactions("user1", null, Comparator.comparing(Transaction::getDate));
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }
//        System.out.println(STR."User1 Balance after transaction: \{phonePeWallet.fetchBalance("user1")}");
//        System.out.println(STR."User2 Balance after transaction: \{phonePeWallet.fetchBalance("user2")}");
//        phonePeWallet.sendMoney("user1", "user2", 101);
//        System.out.println(STR."User1 Balance after transaction: \{phonePeWallet.fetchBalance("user1")}");
//        System.out.println(STR."User2 Balance after transaction: \{phonePeWallet.fetchBalance("user2")}");
    }
}

