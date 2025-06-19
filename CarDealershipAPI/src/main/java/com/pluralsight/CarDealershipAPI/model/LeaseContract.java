// LeaseContract.java
package com.pluralsight.CarDealershipAPI.model;

public class LeaseContract {
    private int    leaseId;
    private int    dealershipId;
    private String vin;
    private double residualValue;
    private double leaseFee;
    private double recordingFee;

    public LeaseContract(int dealershipId, String vin,
                         double residualValue, double leaseFee,
                         double recordingFee) {
        this.dealershipId  = dealershipId;
        this.vin            = vin;
        this.residualValue = residualValue;
        this.leaseFee      = leaseFee;
        this.recordingFee  = recordingFee;
    }

    public int    getLeaseId()         { return leaseId; }
    public void   setLeaseId(int id)   { this.leaseId = id; }
    public int    getDealershipId()    { return dealershipId; }
    public String getVin()             { return vin; }
    public double getResidualValue()   { return residualValue; }
    public double getLeaseFee()        { return leaseFee; }
    public double getRecordingFee()    { return recordingFee; }

    @Override
    public String toString() {
        return "Lease #" + leaseId
                + " | Dealer " + dealershipId
                + " | VIN " + vin
                + " | Residual " + residualValue
                + " | LeaseFee " + leaseFee
                + " | RecFee " + recordingFee;
    }
}
