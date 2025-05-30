package com.ps.products;

import com.ps.toppings.PremiumTopping;
import com.ps.toppings.RegularTopping;
import com.ps.toppings.Topping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sandwich implements Product {
    public static final String PACKAGE_NAME = "com.ps.products";
    public static final ArrayList<String> sizes = new ArrayList<>(
            Arrays.asList(
                "Small 4",
                "Medium 8",
                "Large 12"
            )
    );
    public static final ArrayList<String> breads = new ArrayList<>(
            Arrays.asList(
                "White",
                "Wheat",
                "Rye",
                "Wrap"
            )
    );

    public static final int BASE_SIZE = 4;
    private static final double BREAD_BASE_PRICE = 4;
    private static final double BREAD_INCREMENT_PRICE = 1.5;

    private String bread;
    private int size;
    private ArrayList<Topping> toppings;
    private boolean toasted;

    public Sandwich(String bread, int size, ArrayList<Topping> toppings, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    @Override
    public double calcPrice() {
        double sum = 0;
        int multiple = size / BASE_SIZE; // 1, 2, 3
        sum += BREAD_BASE_PRICE + BREAD_INCREMENT_PRICE * multiple;
        double toppingsBasePrice = toppings.stream().mapToDouble(Topping::getPrice).sum();
        sum += toppingsBasePrice * multiple;
        return sum;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addTopping(Topping topping){
        toppings.add(topping);
    }

    // sort topping to group them by types
    public void sortToppings() {
        toppings.sort(Comparator.comparingInt(t -> getToppingTypeIndex(t.getName())));
    }

    private int getToppingTypeIndex(String name) {
        if (PremiumTopping.meatToppings.contains(name)) return 0;
        if (PremiumTopping.cheeseToppings.contains(name)) return 1;
        if (RegularTopping.veggieToppings.contains(name)) return 2;
        if (RegularTopping.sauceToppings.contains(name)) return 3;
        if (RegularTopping.sideToppings.contains(name)) return 4;
        return 5; // other types
    }

    @Override
    public String toString() {
        return "\nSandwich\n" +
                "Bread type: " + bread + "\n" +
                "Size: " + size + "\n" +
                "Toasted: " + (toasted ? "Yes" : "No") + "\n" +
                "Toppings: " + toppings + "\n" +
                "Sandwich price: $" + calcPrice();
    }
}
