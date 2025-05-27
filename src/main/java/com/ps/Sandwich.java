package com.ps;

import java.util.ArrayList;

public class Sandwich implements Product{
    private ArrayList<Topping> toppings;

    public Sandwich(){
        toppings = new ArrayList<>();
    }

    @Override
    public double calcPrice() {
        return 0;
    }
}
