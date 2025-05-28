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
        System.out.println("Welcome to the \"DELI-cious\" home page!" +
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
        System.out.println("\nWelcome to order page! " +
                "\n1) Add sandwich" +
                "\n2) Add a signature sandwich" +
                "\n3) Add drink" +
                "\n4) Add chips" +
                "\n5) Checkout" +
                "\n0) Cancel order" +
                "\nChoose your option: ");

    }

    private void processAddSandwichRequest(){
        System.out.println("Let's customize your sandwich!" +
                "\nSelect your bread(white, wheat, rye or wrap): ");
        System.out.println("Select the size(4, 8 or 12): ");
        System.out.println("Select topping(s): ");
        processSelectToppingsRequest();
        System.out.println("Would you like your sandwich toasted? ");
    }

    private void processSelectToppingsRequest(){

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

