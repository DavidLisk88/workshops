// Program.java
package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            DealershipDAO dealerDao = new DealershipDAO();

            // If there are no dealerships, then user will be prompted to add a new one.
            List<Dealership> allDealers = dealerDao.getAllDealerships();
            if (allDealers.isEmpty()) {
                System.out.println("No dealerships found—please add one now.");
                addDealershipWorkflow(scanner, dealerDao);
                allDealers = dealerDao.getAllDealerships();
            }

            // 2) Let user select or add dealerships
            int chosenId = -1;
            while (chosenId < 0) {
                System.out.println("\nExisting Dealerships:");
                for (Dealership d : dealerDao.getAllDealerships()) {
                    System.out.println(d);
                }
                System.out.print("Enter ID to use, or type 'add' to add new: ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("add")) {
                    addDealershipWorkflow(scanner, dealerDao);
                } else {
                    try {
                        chosenId = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid. Try again.");
                    }
                }
            }

            // 3) Launch UI with chosen dealership ID
            UserInterface ui = new UserInterface(chosenId, scanner);
            ui.display();

            scanner.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }




    // this method is for adding dealerships
    private static void addDealershipWorkflow(Scanner scanner, DealershipDAO dao) throws Exception {
        while (true) {
            System.out.print("Name (or 'cancel'): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("cancel")) return;

            System.out.print("Address (or 'cancel'): ");
            String address = scanner.nextLine();
            if (address.equalsIgnoreCase("cancel")) return;

            System.out.print("Phone (or 'cancel'): ");
            String phone = scanner.nextLine();
            if (phone.equalsIgnoreCase("cancel")) return;

            System.out.println("\nYou entered:");
            System.out.println("Name:    " + name);
            System.out.println("Address: " + address);
            System.out.println("Phone:   " + phone);
            System.out.print("Confirm? (yes/cancel): ");
            String conf = scanner.nextLine();
            if (conf.equalsIgnoreCase("yes")) {
                Dealership d = new Dealership(name, address, phone);
                dao.addDealership(d);
                System.out.println("Added dealership with ID " + d.getDealershipId());
                return;
            } else {
                System.out.println("Cancelled or not confirmed, restarting entry.");
            }
        }
    }
}
