package com.pluralsight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;
        try {
            File file = new File("inventory.csv");
            Scanner scanner = new Scanner(file);

            // Read dealership info from the first line
            if (scanner.hasNextLine()) {
                String dealershipInfo = scanner.nextLine();
                String[] dealershipParts = dealershipInfo.split("\\|");

                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                dealership = new Dealership(name, address, phone);
            }

            // Read vehicle info from the remaining lines
            while (scanner.hasNextLine()) {
                String vehicleLine = scanner.nextLine();
                String[] parts = vehicleLine.split("\\|");

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        // To be implemented in the next phase
    }
}
