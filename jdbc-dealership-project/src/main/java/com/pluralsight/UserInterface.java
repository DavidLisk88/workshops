package com.pluralsight;

import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private final int                dealershipId;
    private final Scanner            scanner;
    private final VehicleDAO         vehicleDao;
    private final SalesContractDAO   salesDao;
    private final LeaseContractDAO   leaseDao;

    public UserInterface(int dealershipId, Scanner sharedScanner) {
        this.dealershipId = dealershipId;
        this.scanner      = sharedScanner;
        this.vehicleDao   = new VehicleDAO();
        this.salesDao     = new SalesContractDAO();
        this.leaseDao     = new LeaseContractDAO();
    }

 // Main menu method
    public void display() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1) Search vehicles");
            System.out.println("2) Add vehicle");
            System.out.println("3) Remove vehicle");
            System.out.println("4) Sell/Lease vehicle");
            System.out.println("0) Quit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    searchVehicles();
                    break;
                case "2":
                    addVehicle();
                    break;
                case "3":
                    removeVehicle();
                    break;
                case "4":
                    contractVehicle();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Unknown choice. Please enter 0–4.");
            }
        }
    }


    // Method for searching for vehicles
    private void searchVehicles() {
        try {
            System.out.println(
                    "a) By price   b) By make/model   c) By year   d) By color\n" +
                            "e) By mileage f) By type        g) List all"
            );
            System.out.print("Option: ");
            String option = scanner.nextLine().trim().toLowerCase();

            List<Vehicle> results;
            switch (option) {
                case "a":
                    double minPrice = promptDouble("Min price");
                    double maxPrice = promptDouble("Max price");
                    results = vehicleDao.getVehiclesByPriceRange(dealershipId, minPrice, maxPrice);
                    break;
                case "b":
                    String make = promptString("Make");
                    String model = promptString("Model");
                    results = vehicleDao.getVehiclesByMakeAndModel(dealershipId, make, model);
                    break;
                case "c":
                    int minYear = promptInt("Min year");
                    int maxYear = promptInt("Max year");
                    results = vehicleDao.getVehiclesByYearRange(dealershipId, minYear, maxYear);
                    break;
                case "d":
                    String color = promptString("Color");
                    results = vehicleDao.getVehiclesByColor(dealershipId, color);
                    break;
                case "e":
                    int minOdo = promptInt("Min mileage");
                    int maxOdo = promptInt("Max mileage");
                    results = vehicleDao.getVehiclesByMileageRange(dealershipId, minOdo, maxOdo);
                    break;
                case "f":
                    String type = promptString("Type");
                    results = vehicleDao.getVehiclesByType(dealershipId, type);
                    break;
                default:
                    results = vehicleDao.getAllVehicles(dealershipId);
            }

            System.out.println("\n--- Search Results ---");
            if (results.isEmpty()) {
                System.out.println("No vehicles found.");
            } else {
                for (Vehicle v : results) {
                    System.out.println(v);
                }
            }
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }




    // Method for adding vehicles
    private void addVehicle() {
        try {
            String vin = vehicleDao.generateUniqueVin();
            int year = promptInt   ("Year");
            String make = promptString("Make");
            String model = promptString("Model");
            String type = promptString("Type");
            String color = promptString("Color");
            int odometer = promptInt   ("Odometer");
            double price = promptDouble("Price");

            while (true) {
                System.out.println("\n--- Review Vehicle ---");
                System.out.println("vin       : " + vin);
                System.out.println("year      : " + year);
                System.out.println("make      : " + make);
                System.out.println("model     : " + model);
                System.out.println("type      : " + type);
                System.out.println("color     : " + color);
                System.out.println("odometer  : " + odometer);
                System.out.println("price     : " + price);
                System.out.print("Press Y to confirm add, C to cancel, or field name to edit: ");
                String input = scanner.nextLine().trim().toLowerCase();

                // if the user confirms then vehicle is added.
                if (input.equals("y")) {
                    vehicleDao.addVehicle(
                            new Vehicle(vin, year, make, model, type, color, odometer, price),
                            dealershipId
                    );
                    System.out.println("Vehicle added.");
                    return;
                }

                // if user cancels then user is prompted to change which piece of info they want
                if (input.equals("c")) {
                    System.out.println("Add cancelled.");
                    return;
                }
                switch (input) {
                    case "year":     year     = promptInt   ("Year");     break;
                    case "make":     make     = promptString("Make");     break;
                    case "model":    model    = promptString("Model");    break;
                    case "type":     type     = promptString("Type");     break;
                    case "color":    color    = promptString("Color");    break;
                    case "odometer": odometer = promptInt   ("Odometer"); break;
                    case "price":    price    = promptDouble("Price");    break;
                    default:
                        System.out.println("Unknown field. Try year, make, model, type, color, odometer, price.");
                }
            }
        } catch (Exception e) {
            System.out.println("Add error: " + e.getMessage());
        }
    }


    // Method for removing vehicles
    private void removeVehicle() {
        try {
            String vin = promptString("VIN to remove");
            System.out.print("Press Y to confirm removal or C to cancel: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                vehicleDao.removeVehicle(vin, dealershipId);
                System.out.println("Vehicle removed and marked sold.");
            } else {
                System.out.println("Removal cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Remove error: " + e.getMessage());
        }
    }



    // Method for contracting vehicles.
    private void contractVehicle() {
        try {
            String vin = promptString("VIN for contract");
            Vehicle vehicle = vehicleDao.getVehicleByVin(dealershipId, vin);
            if (vehicle == null) {
                System.out.println("Vehicle not found or already sold.");
                return;
            }

            System.out.print("Press 1 for Sale, 2 for Lease, or C to cancel: ");
            String option = scanner.nextLine().trim().toLowerCase();
            if (option.equals("c")) return;

            if (option.equals("1")) {
                // Sale flow
                String saleDate       = promptString("Sale date (YYYY-MM-DD)");
                double salesTaxAmount = promptDouble("Sales tax amount");
                double recordingFee   = promptDouble("Recording fee");
                double processingFee  = promptDouble("Processing fee");
                boolean financed      = promptBoolean("Financed? (true/false)");

                while (true) {
                    SalesContract contract = new SalesContract(
                            dealershipId, vin,
                            salesTaxAmount, recordingFee,
                            processingFee, financed, saleDate
                    );
                    System.out.println("\n--- Review Sale ---");
                    System.out.println(contract);
                    System.out.print("Press Y to confirm, C to cancel, or field name (date/tax/recfee/procfee/financed): ");
                    String in = scanner.nextLine().trim().toLowerCase();
                    if (in.equals("y")) {
                        salesDao.addSalesContract(contract);
                        System.out.println("Sale saved with ID " + contract.getContractId());
                        vehicleDao.removeVehicle(vin, dealershipId);
                        return;
                    }
                    if (in.equals("c")) {
                        System.out.println("Sale cancelled.");
                        return;
                    }
                    switch (in) {
                        case "date":     saleDate       = promptString("Sale date (YYYY-MM-DD)"); break;
                        case "tax":      salesTaxAmount = promptDouble("Sales tax amount");        break;
                        case "recfee":   recordingFee   = promptDouble("Recording fee");          break;
                        case "procfee":  processingFee  = promptDouble("Processing fee");         break;
                        case "financed": financed      = promptBoolean("Financed? (true/false)"); break;
                        default:
                            System.out.println("Unknown field. Use date, tax, recfee, procfee, financed.");
                    }
                }

            } else if (option.equals("2")) {
                // Lease flow
                double residualValue = promptDouble("Residual value");
                double leaseFee      = promptDouble("Lease fee");
                double recFee        = promptDouble("Recording fee");

                while (true) {
                    LeaseContract contract = new LeaseContract(
                            dealershipId, vin, residualValue, leaseFee, recFee
                    );
                    System.out.println("\n--- Review Lease ---");
                    System.out.println(contract);
                    System.out.print("Press Y to confirm, C to cancel, or field name (residual/leasefee/recfee): ");
                    String in = scanner.nextLine().trim().toLowerCase();
                    if (in.equals("y")) {
                        leaseDao.addLeaseContract(contract);
                        System.out.println("Lease saved with ID " + contract.getLeaseId());
                        vehicleDao.removeVehicle(vin, dealershipId);
                        return;
                    }
                    if (in.equals("c")) {
                        System.out.println("Lease cancelled.");
                        return;
                    }
                    switch (in) {
                        case "residual": residualValue = promptDouble("Residual value"); break;
                        case "leasefee": leaseFee      = promptDouble("Lease fee");      break;
                        case "recfee":   recFee        = promptDouble("Recording fee");   break;
                        default:
                            System.out.println("Unknown field. Use residual, leasefee, recfee.");
                    }
                }

            } else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Contract error: " + e.getMessage());
        }
    }

    // ─── Prompt helpers

    private String promptString(String prompt) throws Exception {
        System.out.print(prompt + ": ");
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("Cannot be blank.");
            return promptString(prompt);
        }
        return line;
    }

    private int promptInt(String prompt) throws Exception {
        String text = promptString(prompt);
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
            return promptInt(prompt);
        }
    }

    private double promptDouble(String prompt) throws Exception {
        String text = promptString(prompt);
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
            return promptDouble(prompt);
        }
    }

    private boolean promptBoolean(String prompt) throws Exception {
        String text = promptString(prompt);
        if (text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(text);
        }
        System.out.println("Enter true or false.");
        return promptBoolean(prompt);
    }
}
