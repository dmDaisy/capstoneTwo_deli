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
    javac -d out src/main/java/com/ps/*.java
    ```

3. Run the application.

    ```bash
    java -cp out com.ps.Main
    ```

## Screenshots

Below are a few screenshots of the application in action:

### Buy vehicle

![Screenshot 2025-05-16 at 6 43 09 PM](https://github.com/user-attachments/assets/6e48fe29-d098-4ebf-8264-55c660f0a9b7)

### Home Screen

![Screenshot 2025-05-09 at 11 05 59 PM](https://github.com/user-attachments/assets/3f7bec05-d060-43fe-bae0-bee079606fab)


### Search by features example 1

![Screenshot 2025-05-09 at 9 45 21 PM](https://github.com/user-attachments/assets/8679b20c-9fbe-457d-8cbf-49162aff6dea)


### Search by features example 2

![Screenshot 2025-05-09 at 9 46 12 PM](https://github.com/user-attachments/assets/162bb91a-eed3-4425-91d7-5cb7b5ad0454)


### Search by features example 3

![Screenshot 2025-05-09 at 9 46 40 PM](https://github.com/user-attachments/assets/060ed825-f33d-4daa-9609-37e42db69a3e)


### Invalid input handling

![Screenshot 2025-05-09 at 10 45 46 PM](https://github.com/user-attachments/assets/1cf2aee3-d138-468e-9239-f0ab36f6b917)



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
