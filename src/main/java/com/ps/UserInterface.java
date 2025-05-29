package com.ps;

import com.ps.products.*;
import com.ps.toppings.*;

import java.io.*;
import java.lang.*;
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

    public void display() throws Exception {
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

    private void processNewOrderRequest() throws Exception {
        order = new Order(new ArrayList<>());
        // home page
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
                    running = false;
                    System.out.println("Going back to home page...");
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

        int breadChoiceIndex = selectFromArray(Sandwich.breads, "bread", false);
        String bread = Sandwich.breads.get(breadChoiceIndex);

        int sizeChoiceIndex = selectFromArray(Sandwich.sizes, "size", false);
        int size = (++sizeChoiceIndex) * Sandwich.BASE_SIZE;

        ArrayList<Topping> toppings = processSelectToppingsRequest();

        System.out.println("Would you like your sandwich toasted? (Y)");
        boolean toasted = (scanner.nextLine().trim().equalsIgnoreCase("Y"));

        Sandwich sandwich = new Sandwich(bread, size, toppings, toasted);
        order.getCart().add(sandwich);
        System.out.println("The following sandwich is added to your order: " + "\n" + sandwich);
    }

    private ArrayList<Topping> processSelectToppingsRequest() throws IOException {
        ArrayList<Topping> results = new ArrayList<>();
        // select meat and cheese topping(s)
        addPremiumToppings(results, PremiumTopping.meatToppings, "meat");
        addPremiumToppings(results, PremiumTopping.cheeseToppings, "cheese");
        addRegularToppings(results, RegularTopping.veggieToppings, "veggie");
        addRegularToppings(results, RegularTopping.sauceToppings, "sauce");
        addRegularToppings(results, RegularTopping.sideToppings, "side");
        return results;
    }

    // select and append premium toppings to a given arrayList of Toppings
    private void addPremiumToppings(ArrayList<Topping> addToArray, ArrayList<String> selectFromArray, String type) throws IOException {
        int choiceIndex = selectFromArray(selectFromArray, type, true);
        if(choiceIndex < 0)
            return;
        String name = selectFromArray.get(choiceIndex);
        addToArray.add(new PremiumTopping(name, false));
        System.out.println("Would you like extra? (Y)");
        String input = scanner.nextLine().trim().toUpperCase();
        if(input.equalsIgnoreCase("Y"))
            addToArray.add(new PremiumTopping(name, true));
    }

    // select and append regular toppings to a given arrayList of Toppings
    private void addRegularToppings(ArrayList<Topping> results, ArrayList<String> selectFromArray, String type) throws IOException {
        int choiceIndex = selectFromArray(selectFromArray, type, true);
        while (choiceIndex >= 0){
            String name = selectFromArray.get(choiceIndex);
            results.add(new RegularTopping(name));
            System.out.println("What else would you like? Enter 0 when you've finished. ");
            choiceIndex = getIntInput() - 1;
        }
    }

    private void processAddSignatureSandwichRequest() throws Exception {
//        int choiceIndex = selectFromArray(Sandwich.signatureSandwichs, "signature sandwich", true);
//        if(choiceIndex < 0)
//            return;
//        String className = Sandwich.signatureSandwichs.get(choiceIndex);
//        String packageName = Sandwich.PACKAGE_NAME;
//        String fullClassName = packageName + "." + className;
//        try {
//            Class<?> clazz = Class.forName(fullClassName);
//            Constructor<?> constructor = clazz.getConstructor(String.class);
//            Object signatureSandwich = constructor.newInstance();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void processAddDrinkRequest() throws IOException {
        int choiceIndex = selectFromArray(Drink.sizes, "drink", true);
        if(choiceIndex < 0)
            return;
        order.getCart().add(new Drink(Drink.sizes.get(choiceIndex)));
        System.out.println("Drink added. ");
    }

    private void processAddChipsRequest(){
        System.out.println("Enter Y to confirm adding 1 bag of chips. ");
        if(scanner.nextLine().trim().equalsIgnoreCase("Y")){
            order.getCart().add(new Chips());
            System.out.println("1 bag of chips added. ");
        }
    }

    // return: checkout(boolean)
    private void processCheckoutRequest(){
        if(order.getCart().isEmpty()){
            System.out.println("Nothing added yet, going back to home page...");
            order = null;
            return;
        }
        System.out.println(order);
        System.out.println("Enter Y to confirm checkout, or 0 to cancel: ");
        if(scanner.nextLine().trim().equalsIgnoreCase("Y")){
            OrderFileManager.writeReceipt(order);
            System.out.println("Checkout confirmed. ");
        }
        else{
            System.out.println("Order cancelled. ");
        }
        order = null;
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

    // helper method to print chosen array and prompt the user to select
    // return index of choice in the array,
    // return -1 if skipped
    // generic
    public <T> int selectFromArray(ArrayList<T> list, String type, boolean skippable) throws IOException {
        String skippableText = skippable ? ", enter 0 to skip or cancel" : "";
        System.out.println("Select your " + type + skippableText + ": ");
        printMenu(list);
        int choice = getIntInput();
        if(skippable && choice == 0)
            return -1;
        while (intOutOfBound(choice, list.size()) || (!skippable && choice == 0)){
            System.out.println("Input out of bound, try again");
            choice = getIntInput();
        }
        return --choice;
    }

    // helper method to print a given topping list with index
    // range [1, size of array]
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

