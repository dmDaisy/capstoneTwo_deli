package com.ps;

import com.ps.products.*;
import com.ps.toppings.PremiumTopping;
import com.ps.toppings.RegularTopping;
import com.ps.toppings.Topping;

import java.io.*;
import java.util.*;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private Order order;

    public UserInterface(){
        init();
    }

    private void init(){
        order = null;
    }

    public void display() throws IOException {

        int choice;
        boolean running = true;

        while (running){
            System.out.println("Welcome to the \"DELI-cious\" home page!" +
                    "\n1) New Order" +
                    "\n2) Search order by phone number" +
                    "\n0) Exit" +
                    "\nChoose your option: ");

            choice = getIntInput();
            switch (choice){
                case 1:
                    processNewOrderRequest();
                    break;
                case 2:
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

        order = new Order(new ArrayList<>());

        // prompt user for phone number (optional)
        System.out.println("Would you like to provide your phone number to retrieve your order(s) in the future? (Enter Y to confirm)");
        String answer = scanner.nextLine().trim().toUpperCase();
        if(answer.equalsIgnoreCase("Y")){
            System.out.println("Enter phone number: ");
            order.setPhoneNumber(getIntInput());
        }

        int choice;
        boolean running = true;
        while (running){
            System.out.println("\nWelcome to order page! " +
                    "\n1) Add sandwich" +
                    "\n2) Add a signature sandwich" +
                    "\n3) Add drink" +
                    "\n4) Add chips" +
                    "\n5) Checkout" +
                    "\n0) Cancel order" +
                    "\nChoose your option: ");

            choice = getIntInput();
            switch (choice){
                case 1:
                    processAddSandwichRequest();
                    break;
                case 2:
                    processAddSignatureSandwichRequest();
                    break;
                case 3:
                    processAddDrinkRequest();
                    break;
                case 4:
                    processAddChipsRequest();
                    break;
                case 5:
                    processCheckoutRequest();
                    break;
                case 0:
                    order = null;
                    running = false;
                    System.out.println("Order cancelled. Going back to home page...");
                    break;
                default:
                    System.out.println("Invalid input, try again. ");
            }
        }
    }

    private void processAddSandwichRequest() throws IOException {
        System.out.println("Let's customize your sandwich!");

        int breadChoice = selectFromArray(Sandwich.breads, "bread", false);
        String bread = Sandwich.breads.get(breadChoice - 1);

        int sizeChoice = selectFromArray(Sandwich.sizes, "size", false);
        int size = sizeChoice * Sandwich.BASE_SIZE;

        ArrayList<Topping> toppings = processSelectToppingsRequest();

        System.out.println("Would you like your sandwich toasted? (Y)");
        boolean toasted = (scanner.nextLine().trim().toUpperCase().equalsIgnoreCase("Y"));

        Sandwich sandwich = new Sandwich(bread, size, toppings, toasted);
        order.getCart().add(sandwich);
        System.out.println("The following sandwich added to your order: " + "\n" + sandwich);
    }

    private ArrayList<Topping> processSelectToppingsRequest() throws IOException {
        ArrayList<Topping> results = new ArrayList<>();

        // select meat topping(s)
        int choice = selectFromArray(PremiumTopping.meatToppings, "meat", true);
        if(choice != 0){
            // add topping
            String name = PremiumTopping.meatToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("Do you want extra? (Y)");
            String input = scanner.nextLine().trim().toUpperCase();
            if(input.equalsIgnoreCase("Y"))
                results.add(new PremiumTopping(name, true));
        }

        // select cheese topping(s)
        choice = selectFromArray(PremiumTopping.cheeseToppings, "cheese", true);
        if(choice != 0){
            // add topping
            String name = PremiumTopping.cheeseToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("Do you want extra? (Y)");
            String input = scanner.nextLine().trim().toUpperCase();
            if(input.equalsIgnoreCase("Y"))
                results.add(new PremiumTopping(name, true));
        }

        // select regular topping(s)
        choice = selectFromArray(RegularTopping.regularToppings, "veggie and/or sauce", true);
        while (choice != 0){
            // add topping
            String name = RegularTopping.regularToppings.get(choice - 1);
            results.add(new PremiumTopping(name, false));
            System.out.println("What else? Enter 0 when you finish. ");
            choice = getIntInput();
        }

        return results;
    }

    private void processAddSignatureSandwichRequest(){

    }

    private void processAddDrinkRequest() throws IOException {
        int choice = selectFromArray(Drink.sizes, "drink", true);
        if(choice == 0)
            return;
        order.getCart().add(new Drink(Drink.sizes.get(choice - 1)));
        System.out.println("Drink added. ");
    }

    private void processAddChipsRequest(){
        System.out.println("Enter Y to confirm adding 1 bag of chips. ");
        if(scanner.nextLine().trim().equalsIgnoreCase("Y")){
            order.getCart().add(new Chips());
            System.out.println("1 bag of chips added. ");
        }
    }

    private void processCheckoutRequest(){
        System.out.println("Order checked out. " +
                "\n" + order);
        double totalPrice = 0;
        for (Product product : order.getCart())
            totalPrice += product.calcPrice();
        System.out.println("Total price: " + totalPrice);
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

    // helper method to print chosen array and prompt the user to select a choice
    // return value should be 0 or within range of list size
    public <T> int selectFromArray(ArrayList<T> list, String type, boolean skippable) throws IOException {
        String skippableText = skippable ? ", enter 0 to skip or cancel" : "";
        System.out.println("Select your " + type + skippableText + ": ");
        printMenu(list);
        int choice = getIntInput();

        while (choice != 0 && intOutOfBound(choice, list.size()) || (!skippable && choice == 0)){
            System.out.println("Input out of bound, try again");
            choice = getIntInput();
        }
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

