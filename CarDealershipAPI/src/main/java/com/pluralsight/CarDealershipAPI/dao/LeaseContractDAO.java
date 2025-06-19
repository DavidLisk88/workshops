// LeaseContractDAO.java
package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.LeaseContract;

import java.sql.*;

public class LeaseContractDAO {
//    private static final String INSERT_SQL =
//            "INSERT INTO lease_contracts "
//                    + "(dealership_id, VIN) "
//                    + "VALUES (?, ?,)";
//
//    public void addLeaseContract(LeaseContract contract) throws SQLException {
//        try (
//             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setInt   (1, contract.getDealershipId());
//            ps.setString(2, contract.getVin());
//
//            ps.executeUpdate();
//            try (ResultSet keys = ps.getGeneratedKeys()) {
//                if (keys.next()) {
//                    contract.setLeaseId(keys.getInt(1));
//                }
//            }
//        }
//    }
}
