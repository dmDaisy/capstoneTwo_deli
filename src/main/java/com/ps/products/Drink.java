package com.ps.products;

import java.util.*;

public class Drink implements Product {
    private static final Map<String, Double> SIZE_PRICES_MAP = Map.of(
            "Small", 2.0,
            "Medium", 2.5,
            "Large", 3.0
    );

    public static final ArrayList<String> sizes = new ArrayList<>(SIZE_PRICES_MAP.keySet());

    private String size;

    public Drink(String size) { this.size = size; }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double calcPrice() {
        return SIZE_PRICES_MAP.get(size);
    }
}
