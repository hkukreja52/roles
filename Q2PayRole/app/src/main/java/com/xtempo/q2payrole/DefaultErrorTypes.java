package com.xtempo.q2payrole;

import com.xtempo.networklibrary.ErrorType;

/**
 * Created by Harsha on 11/2/2017.
 */

public class DefaultErrorTypes implements ErrorType {


    private String errorTypeKey;

    DefaultErrorTypes(String errorTypeKey) {
        this.errorTypeKey = errorTypeKey;
    }

    @Override
    public String getErrorTypeKey() {
        return errorTypeKey;
    }

}
