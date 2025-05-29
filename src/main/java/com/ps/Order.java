package com.ps;

import com.ps.products.Product;
import java.util.ArrayList;

public class Order {
    private int phoneNumber;
    private ArrayList<Product> cart;

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

    @Override
    public String toString() {
        return "Order details: " +
                "\nPhone number: " + phoneNumber +
                "\nItems ordered: " + cart;
    }
}
