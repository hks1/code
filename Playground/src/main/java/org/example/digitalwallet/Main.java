package org.example.digitalwallet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final Scanner INPUT_READER = new Scanner(System.in);
    private static final DigitalWalletTransaction DIGITAL_WALLET_TRANSACTION =
            new DigitalWalletTransaction();
    private static final Map<String, DigitalWallet> DIGITAL_WALLETS = new HashMap<>();

    public static void main(String[] args) {
        int numberOfWallets = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfWallets-- > 0){
            String[] wallet = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet;

            if(wallet.length == 2){
                digitalWallet = new DigitalWallet(wallet[0], wallet[1]);
            }else {
                digitalWallet = new DigitalWallet(wallet[0],wallet[1], wallet[2] );
            }
            DIGITAL_WALLETS.put(wallet[0], digitalWallet);
        }

        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        while(numberOfTransactions-- > 0){
            String[] transaction = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet = DIGITAL_WALLETS.get(transaction[0]);

            if(transaction[1].equals("add")){
                try {
                    DIGITAL_WALLET_TRANSACTION.addMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("Wallet credited.");
                }catch (TransactionException e){
                    System.out.println(e.getErrorCode() + ": "+ e.getMessage() + ".");
                }
            }else{
                try{
                    DIGITAL_WALLET_TRANSACTION.payMoney(digitalWallet,Integer.parseInt(transaction[2]));
                    System.out.println("Wallet debited.");
                }catch(TransactionException e){
                    System.out.println(e.getErrorCode() + ": " + e.getMessage() + ".");
                }
            }
        }

        //System.out.println(DIGITAL_WALLETS);
        DIGITAL_WALLETS.keySet()
                .stream()
                .sorted()
                .map(digitalWalletId -> DIGITAL_WALLETS.get(digitalWalletId))
                .forEachOrdered(digitalWallet -> System.out.println(digitalWallet));
    }
}
