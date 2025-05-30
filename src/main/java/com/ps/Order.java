package com.ps;

import com.ps.products.Product;
import java.util.ArrayList;

public class Order {
    private int phoneNumber;
    private ArrayList<Product> cart;
    private final String SEPARATION_LINE = "------------------------------";

    public Order(ArrayList<Product> cart) {
        this.phoneNumber = 0;
        this.cart = cart;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public double calcTotalPrice(){
        double totalPrice = 0;
        for (Product product : cart)
            totalPrice += product.calcPrice();
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("----------Order details----------");
        for (int i = 0; i < cart.size(); i++) {
            sb.append("\nitem "+ (i + 1) + cart.get(i));
            sb.append("\n" + SEPARATION_LINE);
        }
        sb.append("\nTotal price: $").append(calcTotalPrice());
        return sb.toString();
    }
}
