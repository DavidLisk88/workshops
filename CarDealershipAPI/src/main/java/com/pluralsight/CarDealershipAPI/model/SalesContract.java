// SalesContract.java
package com.pluralsight.CarDealershipAPI.model;

public class SalesContract {
    private int    contractId;
    private int    dealershipId;
    private String vin;
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financed;
    private String saleDate;  // "YYYY-MM-DD"

    public SalesContract(int dealershipId, String vin,
                         double salesTaxAmount, double recordingFee,
                         double processingFee, boolean financed,
                         String saleDate) {
        this.dealershipId    = dealershipId;
        this.vin             = vin;
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee    = recordingFee;
        this.processingFee   = processingFee;
        this.financed        = financed;
        this.saleDate        = saleDate;
    }

    public int    getContractId()       { return contractId; }
    public void   setContractId(int id) { this.contractId = id; }
    public int    getDealershipId()     { return dealershipId; }
    public String getVin()              { return vin; }
    public double getSalesTaxAmount()   { return salesTaxAmount; }
    public double getRecordingFee()     { return recordingFee; }
    public double getProcessingFee()    { return processingFee; }
    public boolean isFinanced()         { return financed; }
    public String getSaleDate()         { return saleDate; }

    @Override
    public String toString() {
        return "Sale #" + contractId
                + " | Dealer " + dealershipId
                + " | VIN " + vin
                + " | Date " + saleDate
                + " | Tax " + salesTaxAmount
                + " | RecFee " + recordingFee
                + " | ProcFee " + processingFee
                + " | Financed? " + financed;
    }
}
