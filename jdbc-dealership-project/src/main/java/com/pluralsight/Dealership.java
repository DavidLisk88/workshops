package com.pluralsight;

// Dealership.java
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // inside Dealership class…

    /**
     * Removes this vehicle from inventory.
     * Simply drops it from the list.
     */
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    /**
     * Find a vehicle by its VIN.
     * @return the Vehicle if found, or null otherwise
     */
    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }


    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }
}

