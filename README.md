# DELI-cious CLI Application

## Overview

This is a simple CLI java application for ordering foods from DELI-cious restaurant including sandwiches, drinks and chips. 
Customization can be made in various ways and all ordered placed are saved in .txt files for reference.

## Features

- **Add Sandwich**: With the following options to customize:
    - Bread
    - Size
    - Toppings
    - Toasted
- **Add Signature Sandwich**: Directly order template sandwiches like:
    - BLT
    - Philly
    - BBQ
- **Checkout**: With flexibility to:
    - View before paying
    - Remove item(s) from order
    - Go back to add more
- **Save Receipts**: To .txt files.

## Requirements

- Java 8 or higher

## Setup

1. Clone this repository to your local machine.

    ```bash
    git clone https://github.com/dmDaisy/capstoneTwo_deli
    cd capstoneTwo_deli
    ```

2. Compile the Java files.

    ```bash
    javac -d out $(find src -name "*.java")
    ```

3. Run the application.

    ```bash
    java -cp out com.ps.Main
    ```

## Screenshots

Below are a few screenshots of the application in action:

### Add customized sandwich

![Uploading Screenshot 2025-05-30 at 12.17.58 AM.png…]()

### Add signature sandwich

![Uploading Screenshot 2025-05-30 at 12.20.09 AM.png…]()

### Add drink

![Screenshot 2025-05-30 at 12 21 58 AM](https://github.com/user-attachments/assets/c4479c50-a1f2-4252-8770-1eebafe96ac2)

### Checkout

![Screenshot 2025-05-30 at 12 22 35 AM](https://github.com/user-attachments/assets/a42eee3b-a32e-48e5-b2dc-b14d78e59ab7)

### Program structure diagram



### Interesting Piece of Code
**Helper method to prompt a choice from a generic ArrayList**: This is helpful for avoiding a super large UserInterface.java file, as well as increasing readibility. 

```java
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
        return choice - 1;
    }
