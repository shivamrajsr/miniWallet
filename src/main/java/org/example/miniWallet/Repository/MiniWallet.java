package org.example.miniWallet.Repository;

import org.example.miniWallet.Constants.Constants;
import org.example.miniWallet.Models.User;

import java.util.concurrent.ConcurrentHashMap;

public class MiniWallet {
    private ConcurrentHashMap<String, User> users;
    private static volatile MiniWallet instance;

    private MiniWallet() {
        this.users = new ConcurrentHashMap<>();
    }


    public static MiniWallet getInstance() {
        MiniWallet result = instance;
        if (result == null) {
            synchronized (MiniWallet.class) {
                result = instance;
                if (result == null) {
                    instance = result = new MiniWallet();
                }
            }
        }
        else {
            System.out.println(Constants.WALLET_ALREADY_INITIALIZED);
        }
        return result;
    }
    public User getUser(String userId) {
        return users.get(userId);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
    }

}
