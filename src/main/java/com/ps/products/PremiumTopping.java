package com.ps.products;

import java.util.*;

public class PremiumTopping extends Topping {
    public static final ArrayList<String> meatToppings = new ArrayList<>(
            Arrays.asList(
                    "Steak",
                    "Ham",
                    "Salami",
                    "Roast beef",
                    "Chicken",
                    "Bacon"
            )
    );
    public static final ArrayList<String> cheeseToppings = new ArrayList<>(
            Arrays.asList(
                    "American",
                    "Provolone",
                    "Cheddar",
                    "Swiss"
            )
    );
    private final double MEAT_BASE_PRICE = 1;
    private final double CHEESE_BASE_PRICE = 0.75;

    private boolean isExtra;

    public PremiumTopping(String name, boolean isExtra) {
        super(name);
        this.isExtra = isExtra;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }

    // returns base price for small size sandwich
    // returns 0 if topping not found
    @Override
    public double getPrice(){
        double price = 0;
        if (meatToppings.contains(this.getName())) {
            price = MEAT_BASE_PRICE;
            if(isExtra)
                price *= 0.5;
        }
        else if (cheeseToppings.contains(this.getName())) {
            price = CHEESE_BASE_PRICE;
            if(isExtra)
                price *= 0.4;
        }
        return price;
    }
}
