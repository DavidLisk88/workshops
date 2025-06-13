package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    // call method to find vehicles within price range
                    break;
                case 2:
                    // call method to find vehicles by make/model
                    break;
                case 3:
                    // call method to find vehicles by year range
                    break;
                case 4:
                    // call method to find vehicles by color
                    break;
                case 5:
                    // call method to find vehicles by mileage range
                    break;
                case 6:
                    // call method to find vehicles by type
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    // call method to add a vehicle
                    break;
                case 9:
                    // call method to remove a vehicle
                    break;
                case 10:
                    processContractRequest(scanner);
                    break;

                case 99:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 99);
        scanner.close();
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    private void displayMenu() {
        System.out.println("\n=== Dealership Inventory Menu ===");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("10 - Sell / Lease a Vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }


    /**
     * Handles either a sale or lease flow for one vehicle.
     */
    private void processContractRequest(Scanner scanner) {
        System.out.print("Enter VIN of vehicle: ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // eat newline

        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("No vehicle found with VIN " + vin);
            return;
        }

        // Pick sale or lease
        System.out.print("Enter 1 for Sale, 2 for Lease: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Disallow lease if too old
        if (choice == 2) {
            int year = vehicle.getYear();
            int currentYear = java.time.LocalDate.now().getYear();
            if (currentYear - year > 3) {
                System.out.println("Vehicle is older than 3 years – cannot lease.");
                return;
            }
        }

        // Common inputs
        System.out.print("Enter date (YYYYMMDD): ");
        String date = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        // Create and save contract
        Contract contract;
        ContractFileManager mgr = new ContractFileManager();
        if (choice == 1) {
            // Sale
            System.out.print("Do you want financing? (yes/no): ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract(date, name, email, vehicle, finance);
        } else {
            // Lease
            contract = new LeaseContract(date, name, email, vehicle);
        }

        // Persist contract
        mgr.saveContract(contract);
        // Remove from inventory
        dealership.removeVehicle(vehicle);

        System.out.println("Contract recorded and vehicle removed from inventory.");
    }

}
