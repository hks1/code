package org.example.digitalwallet;

public class DigitalWalletTransaction implements IDigitalWalletTransaction{
    @Override
    public void addMoney(DigitalWallet digitalWallet, double amount) throws TransactionException {
        if(digitalWallet.getAccessCode() == null){
            throw new TransactionException("Unauthorized user", "USER_NOT_AUTHORIZED");
        } else if (amount <= 0) {
            throw new TransactionException("Invalid amount", "INVALID_AMOUNT");
        } else {
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
        }
    }

    @Override
    public void payMoney(DigitalWallet digitalWallet, double amount) throws TransactionException {
        if(digitalWallet.getAccessCode() == null){
            throw new TransactionException("Unauthorized user", "USER_NOT_AUTHORIZED");
        } else if (amount <= 0) {
            throw new TransactionException("Invalid amount", "INVALID_AMOUNT");
        } else if (digitalWallet.getWalletBalance() < amount) {
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
        } else {
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
        }
    }
}
