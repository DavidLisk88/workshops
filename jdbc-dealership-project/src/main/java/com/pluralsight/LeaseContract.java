package com.pluralsight;

/**
 * A lease contract includes a lease fee, residual value,
 * a recording fee (to match examples), and 36-month financing.
 */
public class LeaseContract extends Contract {
    private static final double RESIDUAL_RATE = 0.50;   // 50% of price
    private static final double LEASE_FEE_RATE = 0.07;  // 7% of price
    private static final double ANNUAL_RATE = 0.04;     // 4% APR
    private static final double RECORDING_FEE = 100;    // flat $100

    private double residualValue; // how much it’s worth at end
    private double leaseFee;      // 7% of original price
    private double recordingFee;  // $100

    public LeaseContract(String date, String customerName,
                         String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);

        double price = vehicle.getPrice();
        this.residualValue = price * RESIDUAL_RATE;
        this.leaseFee = price * LEASE_FEE_RATE;
        this.recordingFee = RECORDING_FEE;
    }

    public double getExpectedEndingValue() { return residualValue; }
    public double getLeaseFee()           { return leaseFee; }
    public double getRecordingFee()       { return recordingFee; }

    /**
     * Total price = depreciation (price - residual) + lease fee + recording fee.
     */
    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double depreciation = price - residualValue;
        return depreciation + leaseFee + recordingFee;
    }

    /**
     * 36-month, 4% APR amortized payment on the total.
     */
    @Override
    public double getMonthlyPayment() {
        double principal = getTotalPrice();
        int months = 36;
        double monthlyRate = ANNUAL_RATE / 12.0;
        return (monthlyRate * principal) /
                (1.0 - Math.pow(1.0 + monthlyRate, -months));
    }
}

