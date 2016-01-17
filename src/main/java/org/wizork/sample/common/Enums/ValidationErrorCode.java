package org.wizork.sample.common.Enums;

import org.wizork.sample.validation.ValidationError;

/**
 * Created by nischal.k on 07/12/15.
 */
public enum ValidationErrorCode implements ValidationError{
    InvalidRole(200, "Unauthorized access"),
    InvalidAccess(201, "Unauthorized access");

    private int code;
    private String message;

    private ValidationErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
