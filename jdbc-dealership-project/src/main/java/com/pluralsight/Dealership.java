// Dealership.java
package com.pluralsight;

public class Dealership {
    private int    dealershipId;
    private String name;
    private String address;
    private String phone;

    public Dealership(String name, String address, String phone) {
        this.name    = name;
        this.address = address;
        this.phone   = phone;
    }

    public int    getDealershipId(){
        return dealershipId;
    }

    public void   setDealershipId(int id){
        this.dealershipId = id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }


    @Override
    public String toString() {
        return "ID: " + dealershipId
                + " | Name: " + name
                + " | Address: " + address
                + " | Phone: " + phone;
    }
}

