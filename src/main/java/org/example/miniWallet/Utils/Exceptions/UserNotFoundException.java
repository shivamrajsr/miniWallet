package org.example.miniWallet.Utils.Exceptions;

import javax.naming.NameNotFoundException;

public class UserNotFoundException extends NameNotFoundException {
    public UserNotFoundException(String userId){
        super(STR."user with userId = \{userId}does not exist!");
    }
}
