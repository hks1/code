package org.example.digitalwallet;

public class DigitalWallet {
    String walletId;
    String username;
    String accessCode;
    double walletBalance;

    public DigitalWallet(String walletId, String username) {
        this.walletId = walletId;
        this.username = username;
    }

    public DigitalWallet(String walletId, String username, String accessCode) {
        this.walletId = walletId;
        this.username = username;
        this.accessCode = accessCode;
    }

    public String getWalletId() {
        return walletId;
    }


    public String getUsername() {
        return username;
    }


    public String getAccessCode() {
        return accessCode;
    }

    /*public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }*/

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Override
    public String toString() {
        return "DigitalWallet{" +
                "walletId='" + walletId + '\'' +
                ", username='" + username + '\'' +
                ", accessCode='" + accessCode + '\'' +
                ", walletBalance=" + walletBalance +
                '}';
    }
}
