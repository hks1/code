package org.example.clone;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Porsche porsche = new Porsche("Blue");

        Porsche anotherPorsche = (Porsche) porsche.clone();
        System.out.println(porsche);
        System.out.println(anotherPorsche);


        System.out.println(porsche.equals(anotherPorsche));
        System.out.println(porsche == anotherPorsche);
        System.out.println(porsche.hashCode());
        System.out.println(anotherPorsche.hashCode());
    }
}
