package com.ps.products;

public class Chips implements Product {
    private final double CHIPS_PRICE = 1.5;

    @Override
    public double calcPrice() {
        return CHIPS_PRICE;
    }
}
