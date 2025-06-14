// LeaseContractDAO.java
package com.pluralsight;

import java.sql.*;

public class LeaseContractDAO {
    private static final String INSERT_SQL =
            "INSERT INTO lease_contracts "
                    + "(dealership_id, VIN, `Residual Value`, `Lease Fee`, `Recording Fee`) "
                    + "VALUES (?, ?, ?, ?, ?)";

    public void addLeaseContract(LeaseContract contract) throws SQLException {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt   (1, contract.getDealershipId());
            ps.setString(2, contract.getVin());
            ps.setDouble(3, contract.getResidualValue());
            ps.setDouble(4, contract.getLeaseFee());
            ps.setDouble(5, contract.getRecordingFee());

            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    contract.setLeaseId(keys.getInt(1));
                }
            }
        }
    }
}
