// DealershipDAO.java
package com.pluralsight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {


    // This method inserts and sets the generated ID on the object.
    public void addDealership(Dealership d) throws SQLException {
        String sql = "INSERT INTO dealerships(name, address, phone) VALUES (?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getAddress());
            ps.setString(3, d.getPhone());
            ps.executeUpdate();

            try (ResultSet results = ps.getGeneratedKeys()) {
                if (results.next()) {
                    d.setDealershipId(results.getInt(1));
                }
            }
        }
    }


    // This method returns all dealerships in the database
    public List<Dealership> getAllDealerships() throws SQLException {
        List<Dealership> list = new ArrayList<>();
        String sql = "SELECT dealership_id, name, address, phone FROM dealerships";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dealership d = new Dealership(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                d.setDealershipId(rs.getInt("dealership_id"));
                list.add(d);
            }
        }
        return list;
    }
}

