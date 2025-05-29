package com.ps;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class OrderFileManager {
    // folder name to save readable order files to
    private static final String FOLDER_NAME = "receipts";
//    // CSV file name to search for orders
//    private static final String ORDERS_FILE = "orders.csv";

    public static void writeReceipt(Order order){
        // Use LocalDateTime and DateTimeFormatter
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String timestamp = now.format(formatter);

        // Ensure the folder exists
        // Create folder if it doesn't exist
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate the filename and path
        String filename = FOLDER_NAME + "/" + timestamp + ".txt";
        File file = new File(filename);

        // Write the order details to the file
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Time: " + now.toString() + "\n");
            writer.write(order.toString());
            System.out.println("Receipt saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Failed to write receipt: " + e.getMessage());
        }
    }
}

