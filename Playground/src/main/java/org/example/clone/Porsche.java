package org.example.clone;

public class Porsche implements Car, Cloneable{
    String color;
    public Porsche(String color){
        this.color = color;
    }
    @Override
    public void start() {
        System.out.println("Porsche starting...");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Porsche newPorsche = null;
        try {
            //return (Porsche) super.clone();
            newPorsche = (Porsche) super.clone();
        } catch(Exception e){
            e.printStackTrace();
        }
        return newPorsche;
    }

    @Override
    public String toString() {
        return "Porsche{" +
                "color='" + color + '\'' +
                '}';
    }
}

