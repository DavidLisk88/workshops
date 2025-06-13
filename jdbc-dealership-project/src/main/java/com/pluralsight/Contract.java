package com.pluralsight;

/**
 * Abstract base class for any kind of contract.
 * Holds common info: date, customer name/email, and the vehicle.
 * Cannot be instantiated on its own.
 */
public abstract class Contract {
    private String date;           // date of the contract
    private String customerName;   // who is buying or leasing
    private String customerEmail;  // their email
    private Vehicle vehicle;       // the vehicle being sold or leased

    /**
     * Constructor for a contract.
     * @param date string date (e.g. "20210928")
     * @param customerName buyer/lessee name
     * @param customerEmail buyer/lessee email
     * @param vehicle the Vehicle object
     */
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    // Simple getters/setters for the common fields
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    /**
     * Computes the total price of the contract.
     * (Implementation differs for sales vs. lease.)
     */
    public abstract double getTotalPrice();

    /**
     * Computes the monthly payment.
     * (Implementation differs for sales vs. lease.)
     */
    public abstract double getMonthlyPayment();
}
