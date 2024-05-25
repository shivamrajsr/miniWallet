package org.example.miniWallet.Utils.Exceptions;

import org.example.miniWallet.Constants.Constants;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(){
        super(Constants.USER_ALREADY_EXISTS);
    }
}
