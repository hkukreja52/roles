package com.xtempo.q2payrole;

import com.xtempo.networklibrary.ErrorType;

/**
 * Created by Harsha on 11/3/2017.
 */

public enum Type implements ErrorType {

     USER_UNAUTHORIZED("Unauthorized"),
     TOKEN_EXPIRED("Token_Expired"),
     INPUT_INSUFFICIENT("Input_Insufficient");
    private String errorTypeKey;

    Type(String errorTypeKey) {
        this.errorTypeKey = errorTypeKey;
    }

    @Override
    public String getErrorTypeKey() {
        return errorTypeKey;
    }
}
