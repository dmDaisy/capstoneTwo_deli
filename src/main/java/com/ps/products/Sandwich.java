package com.ps.products;

import java.util.ArrayList;
import java.util.Arrays;

public class Sandwich implements Product {
    private String bread;
    public static final ArrayList<String> sizes = new ArrayList<>(
            Arrays.asList(
                    "Small",
                    "Medium",
                    "Large"
            )
    );
    public static final int BASE_SIZE = 4;
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
        return 0;
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

    @Override
    public String toString() {
        return "Sandwich{" +
                "bread='" + bread + '\'' +
                ", size=" + size +
                ", toppings=" + toppings +
                ", toasted=" + toasted +
                '}';
    }
}
