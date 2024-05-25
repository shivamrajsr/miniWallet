package org.example.miniWallet.Utils.Exceptions;

import org.example.miniWallet.Constants.Constants;
import org.example.miniWallet.Models.User;

public class NotSufficientBalanceException extends Exception{
    public NotSufficientBalanceException(User sender){
        super(STR."\{sender.getName()}"+ Constants.NOT_SUFFICIENT_BALANCE);
    }

}
