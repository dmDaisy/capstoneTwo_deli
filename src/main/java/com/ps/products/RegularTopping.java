package com.ps.products;

import java.util.ArrayList;
import java.util.Arrays;

public class RegularTopping extends Topping {
    public static final ArrayList<String> regularToppings = new ArrayList<>(
            Arrays.asList(
                    "Lettuce",
                    "Peppers",
                    "Onions",
                    "Tomatoes",
                    "Jalapeños",
                    "Cucumbers",
                    "Pickles",
                    "Guacamole",
                    "Mushrooms",
                    "Mayo",
                    "Mustard",
                    "Ketchup",
                    "Ranch",
                    "Thousand Islands",
                    "Vinaigrette",
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
    public double getPrice() {
        return 0;
    }
}
