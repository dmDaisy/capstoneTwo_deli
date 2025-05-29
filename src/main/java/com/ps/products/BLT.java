package com.ps.products;

import com.ps.toppings.*;
import java.util.*;

public class BLT extends Sandwich {

    public BLT() {
        super("White",
                8,
                (ArrayList<Topping>) Arrays.asList(
                    new PremiumTopping("Bacon", false),
                    new PremiumTopping("Cheddar", false),
                    new RegularTopping("Lettuce"),
                    new RegularTopping("Tomato"),
                    new RegularTopping("Ranch")
                ),
                true);
    }
}
