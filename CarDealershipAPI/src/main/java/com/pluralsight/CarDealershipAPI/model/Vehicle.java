// Vehicle.java
package com.pluralsight.CarDealershipAPI.model;

public class Vehicle {
    private String vin;
    private int    year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int    odometer;
    private double price;

    public Vehicle(String vin, int year, String make, String model,
                   String vehicleType, String color, int odometer, double price) {
        this.vin         = vin;
        this.year        = year;
        this.make        = make;
        this.model       = model;
        this.vehicleType = vehicleType;
        this.color       = color;
        this.odometer    = odometer;
        this.price       = price;
    }

    public String getVin()          { return vin; }
    public int    getYear()         { return year; }
    public String getMake()         { return make; }
    public String getModel()        { return model; }
    public String getVehicleType()  { return vehicleType; }
    public String getColor()        { return color; }
    public int    getOdometer()     { return odometer; }
    public double getPrice()        { return price; }

    @Override
    public String toString() {
        return "VIN: " + vin
                + " | Year: " + year
                + " | Make: " + make
                + " | Model: " + model
                + " | Type: " + vehicleType
                + " | Color: " + color
                + " | Odo: " + odometer
                + " | Price: " + price;
    }
}
