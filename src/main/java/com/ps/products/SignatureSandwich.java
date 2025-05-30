package com.ps.products;

import com.ps.toppings.*;

import java.util.*;

public class SignatureSandwich extends Sandwich{

    // encapsulation for scalable project
    public static ArrayList<SignatureSandwich> getMenu() {
        return new ArrayList<>(Arrays.asList(
                blt(),
                philly(),
                bbq()
        ));
    }

//    // for smaller projects that don't need to be scalable
//    public static final ArrayList<SignatureSandwich> menu = new ArrayList<>(
//            Arrays.asList(
//                    blt(),
//                    philly(),
//                    bbq()
//            )
//    );

    private String signatureName;

    public SignatureSandwich(String bread, int size, ArrayList<Topping> toppings, boolean toasted, String signatureName) {
        super(bread, size, toppings, toasted);
        this.signatureName = signatureName;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    private static SignatureSandwich blt() {
        return new SignatureSandwich("White",
                8,
                new ArrayList<>(Arrays.asList(
                        new PremiumTopping("Bacon", false),
                        new PremiumTopping("Cheddar", false),
                        new RegularTopping("Lettuce"),
                        new RegularTopping("Tomato"),
                        new RegularTopping("Ranch")
                )),
                true,
                "BLT");
    }

    private static SignatureSandwich philly() {
        return new SignatureSandwich("White",
                8,
                new ArrayList<>(Arrays.asList(
                        new PremiumTopping("Steak", false),
                        new PremiumTopping("American", false),
                        new RegularTopping("Peppers"),
                        new RegularTopping("Mayo")
                )),
                true,
                "Philly");
    }

    private static SignatureSandwich bbq() {
        return new SignatureSandwich("Wrap",
                8,
                new ArrayList<>(Arrays.asList(
                        new PremiumTopping("Steak", false),
                        new PremiumTopping("American", false),
                        new RegularTopping("Onions"),
                        new RegularTopping("Ketchup")
                )),
                true,
                "BBQ");
    }

    @Override
    public String toString() {
        return "\nSignature " + signatureName + super.toString();
    }

    // clone the current instance
    public SignatureSandwich clone(){
        return new SignatureSandwich(
                getBread(),
                getSize(),
                getToppings(),
                isToasted(),
                getSignatureName()
        );
    }
}
