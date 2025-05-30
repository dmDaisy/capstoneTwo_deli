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
**Utilizing super to write fewer lines of code and increase readibility**: When generating CSV entry for `SalesContract` objects, call `super.toCsvEntry()`
to utilize already-defined method in the parent class to write fewer lines of code and enhance reusability, this is especially helpful for classes with many data fields.

```java
    @Override
    public String toCsvEntry() {
        return  String.format(
                "%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                super.toCsvEntry(),
                getSalesTaxAmount(),
                getRecordingFee(),
                getProcessingFee(),
                getTotalPrice(),
                isFinanced() ? "YES" : "NO",
                getMonthlyPayment()
        );
    }
