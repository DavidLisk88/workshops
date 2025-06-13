package com.pluralsight;

/**
 * A sales contract includes tax, fees, and optional financing.
 */
public class SalesContract extends Contract {
    private static final double TAX_RATE = 0.05;     // 5% sales tax
    private static final double RECORDING_FEE = 100; // flat $100
    private static final double LOW_PRICE_PROCESSING_FEE = 295;
    private static final double HIGH_PRICE_PROCESSING_FEE = 495;

    private double salesTaxAmount;    // computed once in constructor
    private double recordingFee;      // always $100
    private double processingFee;     // $295 or $495
    private boolean financed;         // true if user chose to finance

    /**
     * @param date
     * @param customerName
     * @param customerEmail
     * @param vehicle
     * @param financed true if they want financing
     */
    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean financed) {
        super(date, customerName, customerEmail, vehicle);

        this.financed = financed;
        // calculate the basic fee amounts based on vehicle price
        double price = vehicle.getPrice();
        this.salesTaxAmount = price * TAX_RATE;
        this.recordingFee = RECORDING_FEE;
        // under $10k => lower processing fee, otherwise higher
        this.processingFee = price < 10000 ? LOW_PRICE_PROCESSING_FEE : HIGH_PRICE_PROCESSING_FEE;
    }

    public double getSalesTaxAmount() { return salesTaxAmount; }
    public double getRecordingFee()    { return recordingFee; }
    public double getProcessingFee()   { return processingFee; }
    public boolean isFinanced()        { return financed; }

    /**
     * Total price = base price + tax + recording fee + processing fee.
     */
    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        return price + salesTaxAmount + recordingFee + processingFee;
    }

    /**
     * Monthly payment uses a standard amortization formula.
     * If not financed, returns 0.
     */
    @Override
    public double getMonthlyPayment() {
        if (!financed) {
            return 0.0;
        }

        double principal = getTotalPrice();
        double annualRate;
        int months;

        // determine interest rate and term
        if (principal >= 10000) {
            annualRate = 0.0425; // 4.25%
            months = 48;
        } else {
            annualRate = 0.0525; // 5.25%
            months = 24;
        }

        double monthlyRate = annualRate / 12.0;
        // amortization formula: P * r / (1 - (1 + r)^-n)
        return (monthlyRate * principal) /
                (1.0 - Math.pow(1.0 + monthlyRate, -months));
    }
}
