package com.ps.products;

public class Drink implements Product {
    private final double S_PRICE = 2;
    private final double M_PRICE = 2.5;
    private final double L_PRICE = 3;
    private String size;

    public Drink(String size) {
        this.size = size;
    }

    @Override
    public double calcPrice() {
        String size = this.size.toUpperCase();
        double price = switch (size) {
            case "S" -> S_PRICE;
            case "M" -> M_PRICE;
            case "L" -> L_PRICE;
            default -> throw new IllegalArgumentException("Invalid size: " + size);
        };
        return price;
    }
}
