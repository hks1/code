package org.example.digitalwallet;

public class TransactionException extends Exception{
    String errorMessage;
    String errorCode;

    // USER_NOT_AUTHORIZED
    // INSUFFICIENT_BALANCE

    TransactionException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;

    }

    public String getErrorCode() {
        return errorCode;
    }
}
