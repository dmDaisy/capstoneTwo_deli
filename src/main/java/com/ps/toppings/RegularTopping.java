package com.ps.toppings;

import java.util.ArrayList;
import java.util.Arrays;

public class RegularTopping extends Topping {
    public static final ArrayList<String> veggieToppings = new ArrayList<>(
            Arrays.asList(
                    "Lettuce",
                    "Peppers",
                    "Onions",
                    "Tomatoes",
                    "Jalapeños",
                    "Cucumbers",
                    "Pickles",
                    "Guacamole",
                    "Mushrooms"
            )
    );

    public static final ArrayList<String> sauceToppings = new ArrayList<>(
            Arrays.asList(
                    "Mayo",
                    "Mustard",
                    "Ketchup",
                    "Ranch",
                    "Thousand Islands",
                    "Vinaigrette"
            )
    );

    public static final ArrayList<String> sideToppings = new ArrayList<>(
            Arrays.asList(
                    "Side: Au jus",
                    "Side: Mayo",
                    "Side: Mustard",
                    "Side: Ketchup",
                    "Side: Ranch",
                    "Side: Thousand Islands",
                    "Side: Vinaigrette"
            )
    );

    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(){
        return 0;
    }
}
