package org.example.miniWallet.Service;

import org.example.miniWallet.Constants.Constants;
import org.example.miniWallet.Models.TopUpMode;
import org.example.miniWallet.Models.Transaction;
import org.example.miniWallet.Models.User;
import org.example.miniWallet.Repository.MiniWallet;
import org.example.miniWallet.Utils.Exceptions.NotSufficientBalanceException;
import org.example.miniWallet.Utils.Exceptions.TransactionNotSuccessfulException;
import org.example.miniWallet.Utils.Exceptions.UserAlreadyExistsException;
import org.example.miniWallet.Utils.Exceptions.UserNotFoundException;

import java.util.*;

public class MiniWalletServiceImpl implements MiniWalletService {
    private MiniWallet miniWallet;

    public void initializeMiniWallet() {
        this.miniWallet = MiniWallet.getInstance();
    }

    @Override
    public void registerUser(String userId, String name) throws UserAlreadyExistsException {
        if(miniWallet.getUser(userId)!=null){
             throw new UserAlreadyExistsException();
        }
        miniWallet.registerUser(new User(userId,name));
    }

    @Override
    public void topUpWallet(String userId, double amount,TopUpMode mode) {
        // different payment  strategies
        User user = miniWallet.getUser(userId);
        if (user != null) {
            user.getWallet().topUp(amount);
        }
    }

    @Override
    public double fetchBalance(String userId) throws UserNotFoundException {
        User user = miniWallet.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user.getWallet().getBalance();
    }

    @Override
    public void sendMoney(String senderId, String receiverId, double amount) throws NotSufficientBalanceException, UserNotFoundException, TransactionNotSuccessfulException {
            User sender = miniWallet.getUser(senderId);
            User receiver = miniWallet.getUser(receiverId);
            if(sender==null)  throw new UserNotFoundException(senderId);
            if(receiver==null) throw new UserNotFoundException(receiverId);
            if(sender.getWallet().getBalance()< amount) {
                throw new NotSufficientBalanceException(sender);
            }
            boolean amountDeductedFromSenderWallet = sender.getWallet().deductMoney(amount);
            boolean amountAddedToReceiverWallet = receiver.getWallet().receiveMoney(amount);

            if (amountAddedToReceiverWallet && amountDeductedFromSenderWallet) {
                String offer = checkOffers(sender, receiver, amount);
                Transaction transaction = new Transaction(senderId, receiverId, amount, new Date(), offer);
                sender.getWallet().addTransaction(transaction);
                receiver.getWallet().addTransaction(transaction);
            }
            else throw new TransactionNotSuccessfulException();

    }

    @Override
    public String checkOffers(User sender, User receiver, double amount) {
        String offer = null;
        if (amount > 100 && !sender.isFirstMoreThan100TransactionMade()) {
            offer = Constants.CASHBACK;
            sender.getWallet().receiveMoney(amount * 0.1);
            sender.setFirstMoreThan100TransactionMade(true);
        } else if (sender.getWallet().getBalance() == receiver.getWallet().getBalance()) {
             offer = Constants.EQUAL_BALANCE_OFFER;
            sender.getWallet().receiveMoney(amount*0.05);
            receiver.getWallet().receiveMoney(amount*0.05);
        }
        return offer;
    }

    @Override
    public List<Transaction> getTransactions(String userId, String filterUserId, Comparator<Transaction> sorter) {
        User user = miniWallet.getUser(userId);
        if (user != null) {
            List<Transaction> transactions = new ArrayList<>();
            if(filterUserId!=null) transactions = user.getWallet().getFilteredTransactions(user.getWallet().getTransactionHistory(), filterUserId);
            if (sorter != null) {
                transactions = user.getWallet().getSortedTransactions(transactions,sorter);
            }
            return transactions;
        }
        return Collections.emptyList();
    }
}
