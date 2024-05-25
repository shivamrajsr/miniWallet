package org.example.miniWallet.Utils.Exceptions;

import org.example.miniWallet.Constants.Constants;

public class TransactionNotSuccessfulException extends Exception{
    public TransactionNotSuccessfulException(){
        super(Constants.TRANSACTION_NOT_SUCCESSFUL);
    }

}
