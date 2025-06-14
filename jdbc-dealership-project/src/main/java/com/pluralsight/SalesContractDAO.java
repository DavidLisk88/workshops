// SalesContractDAO.java
package com.pluralsight;

import java.sql.*;

public class SalesContractDAO {
    private static final String INSERT_SQL =
            "INSERT INTO sales_contracts "
                    + "(dealership_id, VIN, `Sales Tax Amount`, `Recording Fee`, `Processing Fee`, Financed, `Sale Date`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public void addSalesContract(SalesContract contract) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt   (1, contract.getDealershipId());
            ps.setString(2, contract.getVin());
            ps.setDouble(3, contract.getSalesTaxAmount());
            ps.setDouble(4, contract.getRecordingFee());
            ps.setDouble(5, contract.getProcessingFee());
            ps.setBoolean(6, contract.isFinanced());
            ps.setString(7, contract.getSaleDate());

            ps.executeUpdate();
            try (ResultSet results = ps.getGeneratedKeys()) {
                if (results.next()) {
                    contract.setContractId(results.getInt(1));
                }
            }
        }
    }
}
