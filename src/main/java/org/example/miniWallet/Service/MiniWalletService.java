package org.example.miniWallet.Service;

import org.example.miniWallet.Models.TopUpMode;
import org.example.miniWallet.Models.Transaction;
import org.example.miniWallet.Models.User;
import org.example.miniWallet.Utils.Exceptions.NotSufficientBalanceException;
import org.example.miniWallet.Utils.Exceptions.TransactionNotSuccessfulException;
import org.example.miniWallet.Utils.Exceptions.UserAlreadyExistsException;
import org.example.miniWallet.Utils.Exceptions.UserNotFoundException;

import java.util.Comparator;
import java.util.List;

public interface MiniWalletService {

    void registerUser(String userId, String name) throws UserAlreadyExistsException;

    void topUpWallet(String userId, double amount, TopUpMode mode);

    double fetchBalance(String userId) throws UserNotFoundException;

    void sendMoney(String senderId, String receiverId, double amount) throws NotSufficientBalanceException, UserNotFoundException, TransactionNotSuccessfulException;

    String checkOffers(User sender, User receiver, double amount);

    List<Transaction> getTransactions(String userId, String filterUserId, Comparator<Transaction> sorter);
}
