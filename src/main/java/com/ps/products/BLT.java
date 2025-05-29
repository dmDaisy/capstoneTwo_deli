package com.ps.products;

import com.ps.toppings.Topping;

import java.util.*;

public class BLT extends Sandwich {
    public BLT(String bread, int size, ArrayList<Topping> toppings, boolean toasted) {
        super(bread, size, toppings, toasted);
    }
}
