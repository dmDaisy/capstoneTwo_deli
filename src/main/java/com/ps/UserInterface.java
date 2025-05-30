package com.ps;

import com.ps.products.*;
import com.ps.toppings.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final String SEPARATION_LINE = "------------------------------";
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
            System.out.println(SEPARATION_LINE + "\nWelcome to the \"DELI-cious\" home page!" +
                    "\n1) New Order" +
                    "\n0) Exit" +
                    "\nChoose your option: ");
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

    private void processNewOrderRequest() throws Exception {

        order = new Order(new ArrayList<>());

        int choice;
        boolean running = true;
        while (running){
            System.out.println(SEPARATION_LINE + "\nWelcome to order page! " +
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
                    if(processCheckoutRequest()){
                        running = false;
                        System.out.println("Going back to home page...");
                    }
                    break;
                case 0:
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
        System.out.println("The following sandwich is added to your order: " + "\n" + SEPARATION_LINE + sandwich);
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
    private void addRegularToppings(ArrayList<Topping> addToArray, ArrayList<String> selectFromArray, String type) throws IOException {
        int choiceIndex = selectFromArray(selectFromArray, type, true);
        while (choiceIndex >= 0){
            addToArray.add(new RegularTopping(selectFromArray.get(choiceIndex)));
            System.out.println("What else would you like? Enter 0 when you've finished. ");
            choiceIndex = getInBoundIntInput(0, selectFromArray.size()) - 1;
        }
    }

    private void processAddSignatureSandwichRequest() throws Exception {
        // get array list of names of signature sandwiches
        ArrayList<String> names = SignatureSandwich.getMenu().stream()
                .map(SignatureSandwich::getSignatureName)
                .collect(Collectors.toCollection(ArrayList::new));
        int choiceIndex = selectFromArray(names, "signature sandwich", true);
        if(choiceIndex < 0) // didn't choose
            return;
        // get a clone of the chosen signature sandwich
        SignatureSandwich chosen = SignatureSandwich.getMenu().get(choiceIndex).clone();
        System.out.println("Here are the " + chosen.getSignatureName() + " sandwich details: \n" + SEPARATION_LINE + chosen);
        // customization
        customizeSandwich(chosen);
        order.getCart().add(chosen);
        System.out.println("The following sandwich is added to your order: " + "\n" + SEPARATION_LINE + chosen);
    }

    private void customizeSandwich(Sandwich sandwich) throws IOException {
        // remove topping(s)
        System.out.println("Would you like to remove topping(s)? (Y)");
        if(scanner.nextLine().trim().equalsIgnoreCase("Y")){
            int choiceIndex = 999;
            while (!sandwich.getToppings().isEmpty()){
                choiceIndex = selectFromArray(sandwich.getToppings(), "topping(s) to remove", true);
                if(choiceIndex < 0)
                    break;
                sandwich.getToppings().remove(choiceIndex);
            }
        }
        // add topping(s)
        System.out.println("Would you like to add topping(s)? (Y)");
        if(scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            sandwich.getToppings().addAll(processSelectToppingsRequest());
        }

        sandwich.sortToppings();
    }

    private void processAddDrinkRequest() throws IOException {
        int choiceIndex = selectFromArray(Drink.sizes, "drink", true);
        if(choiceIndex < 0) // didn't choose
            return;
        int flavorIndex = selectFromArray(Drink.flavors, "flavor", false);
        order.getCart().add(new Drink(Drink.sizes.get(choiceIndex), Drink.flavors.get(flavorIndex)));
        System.out.println("Drink added. ");
    }

    private void processAddChipsRequest() throws IOException {
        int flavorIndex = selectFromArray(Chips.flavors, "chips flavor", true);
        if(flavorIndex < 0) // didn't choose
            return;
        order.getCart().add(new Chips(Chips.flavors.get(flavorIndex)));
        System.out.println("1 bag of chips added. ");
    }

    // return: can go back to home page (boolean)
    private boolean processCheckoutRequest(){
        if(order.getCart().isEmpty()){
            System.out.println("Nothing added yet :(");
            return false;
        }
        System.out.println(order);
        System.out.println("Enter Y to confirm checkout, C to cancel, or anything else to go back to add more to your order: ");
        String choice = scanner.nextLine().trim().toUpperCase();
        if(choice.equalsIgnoreCase("Y")){ // confirm checkout
            OrderFileManager.writeReceipt(order);
            System.out.println("Checkout confirmed. ");
            return true;
        }
        else if(choice.equalsIgnoreCase("C")){ // order cancelled
            System.out.println("Order cancelled. ");
            return true;
        }
        else{
            return false;
        }
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
    public <T> int selectFromArray(ArrayList<T> list, String description, boolean skippable) throws IOException {
        String skippableText = skippable ? ", enter 0 to skip or cancel" : "";
        System.out.println("Select your " + description + skippableText + ": ");
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

    // [min, max] inclusive
    private int getInBoundIntInput(int min, int max){
        int input = getIntInput();
        while (input < min || input > max){
            System.out.println("Input out of bound, try again. ");
            input = getIntInput();
        }
        return input;
    }
}