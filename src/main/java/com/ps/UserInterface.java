package com.ps;

import com.ps.products.PremiumTopping;
import com.ps.products.RegularTopping;
import com.ps.products.Topping;

import java.io.IOException;
import java.util.*;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private Order order;

    public UserInterface(){
        init();
    }

    private void init(){

    }

    public void display() throws IOException {
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

    private void processNewOrderRequest() throws IOException {
        System.out.println("\nWelcome to order page! " +
                "\n1) Add sandwich" +
                "\n2) Add a signature sandwich" +
                "\n3) Add drink" +
                "\n4) Add chips" +
                "\n5) Checkout" +
                "\n0) Cancel order" +
                "\nChoose your option: ");

        int choice;
        boolean running = true;
        while (running){
            choice = getIntInput();
            switch (choice){
                case 1:
                    processAddSandwichRequest();
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

    private void processAddSandwichRequest() throws IOException {
        System.out.println("Let's customize your sandwich!" +
                "\nSelect your bread(white, wheat, rye or wrap): ");
        System.out.println("Select the size(4, 8 or 12): ");

        ArrayList<Topping> toppings = processSelectToppingsRequest();

        System.out.println("Would you like your sandwich toasted? ");
    }

    private ArrayList<Topping> processSelectToppingsRequest() throws IOException {
        ArrayList<Topping> results = new ArrayList<>();

        // select meat topping(s)
        int choice = selectFromArray(PremiumTopping.meatToppings, "meat");
        if(choice != 0){
            // add topping
            String name = PremiumTopping.meatToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("Do you want extra? (Y/N)");
            String input = scanner.nextLine().trim().toUpperCase();
            if(input.equalsIgnoreCase("Y"))
                results.add(new PremiumTopping(name, true));
        }

        // select cheese topping(s)
        choice = selectFromArray(PremiumTopping.cheeseToppings, "cheese");
        if(choice != 0){
            // add topping
            String name = PremiumTopping.cheeseToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("Do you want extra? (Y/N)");
            String input = scanner.nextLine().trim().toUpperCase();
            if(input.equalsIgnoreCase("Y"))
                results.add(new PremiumTopping(name, true));
        }

        // select regular topping(s)
        choice = selectFromArray(RegularTopping.regularToppings, "veggie and/or sauce");
        while (choice != 0){
            // add topping
            String name = RegularTopping.regularToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("What else? Enter 0 when you finish. ");
            choice = getIntInput();
        }

        return results;
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

    // helper method to print an array and prompt the user to select a choice
    // return value should be 0 or within range of list size
    public <T> int selectFromArray(ArrayList<T> list, String type) throws IOException {
        System.out.println("Select your " + type + ", enter 0 to skip: ");
        printMenu(list);
        int choice = getIntInput();
        if(choice != 0 && intOutOfBound(choice, list.size()))
            throw new IOException("Selection out of bound. ");
        return choice;
    }

    // helper method to print a given topping list with index
    private <T> void printMenu(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
    }


    // helper method to check if an int inpput is out of range of [1, arraySize], inclusively
    private boolean intOutOfBound(int index, int arraySize){
        return index < 1 || index > arraySize;
    }
}

