package com.ps.products;

import java.util.ArrayList;
import java.util.Arrays;

public class Chips implements Product {
    private final double CHIPS_PRICE = 1.5;

    public static final ArrayList<String> flavors = new ArrayList<>(
            Arrays.asList(
                    "Cucumber",
                    "Octopus",
                    "Lobster",
                    "Spaghetti",
                    "Texas BBQ"
            )
    );

    private String flavor;

    public Chips(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double calcPrice() { return CHIPS_PRICE; }

    @Override
    public String toString() {
        return "\n1 bag of " + flavor + " Chips $" + calcPrice();
    }
}
