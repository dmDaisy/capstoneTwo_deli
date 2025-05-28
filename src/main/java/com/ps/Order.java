package com.ps;

import com.ps.products.Product;
import java.util.ArrayList;

public class Order {
    private String customerName;
    private String phoneNumber;
    private ArrayList<Product> cart;

    public Order(String customerName, String phoneNumber, ArrayList<Product> cart) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cart=" + cart +
                '}';
    }
}
