package com.ps;

public class PremiumTopping extends Topping{
    private String name;
    private double price;

    public PremiumTopping(String name) {
        super(name);
    }

    public double getPrice(){
        return 0;
    }
}
