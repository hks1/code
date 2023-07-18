package org.example.iusevisitor;

public class Hours {


    int hours;
    double rate; // hourly rate

    public Hours(){}
    public Hours(int hours, double rate){
        this.hours = hours;
        this.rate = rate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
