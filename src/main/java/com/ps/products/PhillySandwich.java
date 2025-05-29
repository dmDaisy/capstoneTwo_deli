package com.ps.products;

import com.ps.toppings.PremiumTopping;
import com.ps.toppings.RegularTopping;
import com.ps.toppings.Topping;

import java.util.*;

public class PhillySandwich extends Sandwich {

    public PhillySandwich() {
        super("White",
                8,
                (ArrayList<Topping>) Arrays.asList(
                        new PremiumTopping("Steak", false),
                        new PremiumTopping("American", false),
                        new RegularTopping("Peppers"),
                        new RegularTopping("Mayo")
                ),
                true);
    }
}
