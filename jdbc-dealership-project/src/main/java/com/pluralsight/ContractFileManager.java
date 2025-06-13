package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Appends contracts to a single CSV file.
 * Each line is either a SALE or LEASE contract.
 */
public class ContractFileManager {
    private static final String FILE_NAME = "src/main/resources/contracts.csv";

    /**
     * Open the file in append mode, write one line for this contract.
     */
    public void saveContract(Contract contract) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            StringBuilder sb = new StringBuilder();

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                sb.append("SALE|")
                        .append(sc.getDate()).append("|")
                        .append(sc.getCustomerName()).append("|")
                        .append(sc.getCustomerEmail()).append("|")
                        .append(sc.getVehicle().getVin()).append("|")
                        .append(sc.getVehicle().getYear()).append("|")
                        .append(sc.getVehicle().getMake()).append("|")
                        .append(sc.getVehicle().getModel()).append("|")
                        .append(sc.getVehicle().getVehicleType()).append("|")
                        .append(sc.getVehicle().getColor()).append("|")
                        .append(sc.getVehicle().getOdometer()).append("|")
                        .append(sc.getVehicle().getPrice()).append("|")
                        .append(String.format("%.2f", sc.getSalesTaxAmount())).append("|")
                        .append(String.format("%.2f", sc.getRecordingFee())).append("|")
                        .append(String.format("%.2f", sc.getProcessingFee())).append("|")
                        .append(String.format("%.2f", sc.getTotalPrice())).append("|")
                        .append(sc.isFinanced() ? "YES" : "NO").append("|")
                        .append(String.format("%.2f", sc.getMonthlyPayment()));
            }
            else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;
                sb.append("LEASE|")
                        .append(lc.getDate()).append("|")
                        .append(lc.getCustomerName()).append("|")
                        .append(lc.getCustomerEmail()).append("|")
                        .append(lc.getVehicle().getVin()).append("|")
                        .append(lc.getVehicle().getYear()).append("|")
                        .append(lc.getVehicle().getMake()).append("|")
                        .append(lc.getVehicle().getModel()).append("|")
                        .append(lc.getVehicle().getVehicleType()).append("|")
                        .append(lc.getVehicle().getColor()).append("|")
                        .append(lc.getVehicle().getOdometer()).append("|")
                        .append(lc.getVehicle().getPrice()).append("|")
                        .append(String.format("%.2f", lc.getExpectedEndingValue())).append("|")
                        .append(String.format("%.2f", lc.getLeaseFee())).append("|")
                        .append(String.format("%.2f", lc.getTotalPrice())).append("|")
                        .append(String.format("%.2f", lc.getMonthlyPayment()));
            }

            writer.println(sb.toString());
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}

