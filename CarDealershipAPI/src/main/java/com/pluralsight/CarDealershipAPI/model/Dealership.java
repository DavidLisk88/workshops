// Dealership.java
package com.pluralsight.CarDealershipAPI.model;

public class Dealership {
    private int    dealershipId;
    private String name;
    private String address;
    private String phone;

    public Dealership(){

    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + dealershipId
                + " | Name: " + name
                + " | Address: " + address
                + " | Phone: " + phone;
    }
}

