package com.ps;

import java.util.*;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private Order order;

    public UserInterface(){
        init();
    }

    private void init(){

    }

    public void display(){
        System.out.println("Welcome to the \"DELI-cious\" home screen!" +
                "\n1) New Order" +
                "\n0) Exit" +
                "\nChoose your option: ");

        int choice;
        boolean running = true;

        while (running){
            choice = getIntInput();
            switch (choice){
                case 1:
                    processNewOrderRequest();
                    break;
                case 0:
                    System.out.println("Thank you for using our app, see you again! ");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, try again. ");
            }
        }
    }

    private void processNewOrderRequest(){
        System.out.println("Welcome to order screen! ");
    }

    private void checkout(){

    }

    // helper method to get user int input and consumes redundant \n
    private int getIntInput(){
        while( ! scanner.hasNextInt()){
            System.out.println("Invalid input, enter an integer: ");
            scanner.nextLine(); // or scanner.next() ?
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // consumes redundant \n, or scanner.next() ?
        return result;
    }
}

