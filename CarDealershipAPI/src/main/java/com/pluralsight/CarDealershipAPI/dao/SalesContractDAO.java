// SalesContractDAO.java
package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.SalesContract;

import java.sql.*;

public class SalesContractDAO {
//    private static final String INSERT_SQL =
//            "INSERT INTO sales_contracts "
//                    + "(dealership_id, VIN,`Sale Date`) "
//                    + "VALUES (?, ?, ?)";
//
//    public void addSalesContract(SalesContract contract) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setInt   (1, contract.getDealershipId());
//            ps.setString(2, contract.getVin());
//            ps.setString(3, contract.getSaleDate());
//
//            ps.executeUpdate();
//            try (ResultSet results = ps.getGeneratedKeys()) {
//                if (results.next()) {
//                    contract.setContractId(results.getInt(1));
//                }
//            }
//        }
//    }
}
