package org.example;

import org.example.iusevisitor.Hours;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int hours = Arrays.asList(1,2,3,4).stream().reduce((a, b) -> a+b).get();
        System.out.println(hours);

        int totalHours = Arrays.asList(new Hours(8, 15) ,
                new Hours(7, 15.5),
                new Hours(6, 14))
                .stream()
                .map(a -> a.getHours())
                .reduce((a,b) -> a+b)
                .get();
        double totalPay = Arrays.asList(new Hours(8, 15) ,
                        new Hours(7, 15.5),
                        new Hours(6, 14))
                .stream()
                .map(a -> a.getHours() * a.getRate())
                .reduce((a,b) -> a+b)
                .get();

        System.out.println("total hours worked; " + totalHours + ", total pay: " + totalPay);
    }
}