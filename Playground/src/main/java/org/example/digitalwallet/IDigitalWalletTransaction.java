package org.example.digitalwallet;

public interface IDigitalWalletTransaction {
    public void addMoney(DigitalWallet digitalWallet, double amount) throws TransactionException;
    public void payMoney(DigitalWallet digitalWallet, double amount) throws TransactionException;
}
