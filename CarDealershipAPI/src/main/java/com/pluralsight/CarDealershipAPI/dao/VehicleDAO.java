// VehicleDAO.java
package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleDAO {
    // Base SELECT for unsold vehicles at a given dealership
//    private static final String BASE_SELECT =
//            "SELECT v.VIN, v.year, v.make, v.model, v.vehicletype, v.color, v.odometer, v.price "
//                    + "FROM vehicles v "
//                    + "JOIN inventory i ON v.VIN = i.VIN "
//                    + "WHERE i.dealership_id = ? AND v.sold = 0 ";
//
//    private static final String SQL_BY_PRICE   = BASE_SELECT + "AND v.price BETWEEN ? AND ?";
//    private static final String SQL_BY_MAKE    = BASE_SELECT + "AND v.make = ? AND v.model = ?";
//    private static final String SQL_BY_YEAR    = BASE_SELECT + "AND v.year BETWEEN ? AND ?";
//    private static final String SQL_BY_COLOR   = BASE_SELECT + "AND v.color = ?";
//    private static final String SQL_BY_MILEAGE = BASE_SELECT + "AND v.odometer BETWEEN ? AND ?";
//    private static final String SQL_BY_TYPE    = BASE_SELECT + "AND v.vehicletype = ?";
//    private static final String SQL_SELECT_ALL = BASE_SELECT;
//    private static final String SQL_BY_VIN     = BASE_SELECT + "AND v.VIN = ?";
//
//    public boolean existsVin(String vin) throws SQLException {
//        String sql = "SELECT COUNT(*) FROM vehicles WHERE VIN = ?";
//        try (Connection conn = Database.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, vin);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            boolean exists = rs.getInt(1) > 0;
//            rs.close();
//            return exists;
//        }
//    }
//
//
//    // Made a method for generating a random VIN.
//    public String generateUniqueVin() throws SQLException {
//        Random random = new Random();
//        String vin;
//        do {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < 17; i++) {
//                sb.append(random.nextInt(10));  // 0–9
//            }
//            vin = sb.toString();
//        } while (existsVin(vin));
//        return vin;
//    }
//
//
//
//   // Below are all the search methods
//
//    public List<Vehicle> getVehiclesByPriceRange(int dealerId, double min, double max) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_PRICE)) {
//            ps.setInt(1, dealerId);
//            ps.setDouble(2, min);
//            ps.setDouble(3, max);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getVehiclesByMakeAndModel(int dealerId, String make, String model) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_MAKE)) {
//            ps.setInt   (1, dealerId);
//            ps.setString(2, make);
//            ps.setString(3, model);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getVehiclesByYearRange(int dealerId, int minYear, int maxYear) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_YEAR)) {
//            ps.setInt(1, dealerId);
//            ps.setInt(2, minYear);
//            ps.setInt(3, maxYear);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getVehiclesByColor(int dealerId, String color) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_COLOR)) {
//            ps.setInt   (1, dealerId);
//            ps.setString(2, color);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getVehiclesByMileageRange(int dealerId, int minOdo, int maxOdo) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_MILEAGE)) {
//            ps.setInt(1, dealerId);
//            ps.setInt(2, minOdo);
//            ps.setInt(3, maxOdo);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getVehiclesByType(int dealerId, String type) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_TYPE)) {
//            ps.setInt   (1, dealerId);
//            ps.setString(2, type);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public List<Vehicle> getAllVehicles(int dealerId) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL)) {
//            ps.setInt(1, dealerId);
//            return mapList(ps.executeQuery());
//        }
//    }
//    public Vehicle getVehicleByVin(int dealerId, String vin) throws SQLException {
//        try (Connection connection = Database.getConnection();
//             PreparedStatement ps = connection.prepareStatement(SQL_BY_VIN)) {
//            ps.setInt   (1, dealerId);
//            ps.setString(2, vin);
//            ResultSet rs = ps.executeQuery();
//            Vehicle v = rs.next() ? mapRow(rs) : null;
//            rs.close();
//            return v;
//        }
//    }
//
//    /** Phase 2: add & remove **/
//    public void addVehicle(Vehicle vehicle, int dealerId) throws SQLException {
//        try (Connection connection = Database.getConnection()) {
//            // 1) insert into vehicles
//            PreparedStatement ps1 = connection.prepareStatement(
//                    "INSERT INTO vehicles(VIN,year,make,model,vehicletype,color,odometer,price) "
//                            + "VALUES(?,?,?,?,?,?,?,?)"
//            );
//            ps1.setString(1, vehicle.getVin());
//            ps1.setInt   (2, vehicle.getYear());
//            ps1.setString(3, vehicle.getMake());
//            ps1.setString(4, vehicle.getModel());
//            ps1.setString(5, vehicle.getVehicleType());
//            ps1.setString(6, vehicle.getColor());
//            ps1.setInt   (7, vehicle.getOdometer());
//            ps1.setDouble(8, vehicle.getPrice());
//            ps1.executeUpdate();
//            ps1.close();
//
//            // 2) insert into inventory
//            PreparedStatement ps2 = connection.prepareStatement(
//                    "INSERT INTO inventory(dealership_id,VIN) VALUES(?,?)"
//            );
//            ps2.setInt   (1, dealerId);
//            ps2.setString(2, vehicle.getVin());
//            ps2.executeUpdate();
//            ps2.close();
//        }
//    }
//    public void removeVehicle(String vin, int dealerId) throws SQLException {
//        try (Connection connection = Database.getConnection()) {
//            // 1) delete from inventory
//            PreparedStatement ps1 = connection.prepareStatement(
//                    "DELETE FROM inventory WHERE dealership_id=? AND VIN=?"
//            );
//            ps1.setInt   (1, dealerId);
//            ps1.setString(2, vin);
//            ps1.executeUpdate();
//            ps1.close();
//
//            // 2) mark sold
//            PreparedStatement ps2 = connection.prepareStatement(
//                    "UPDATE vehicles SET sold=1 WHERE VIN=?"
//            );
//            ps2.setString(1, vin);
//            ps2.executeUpdate();
//            ps2.close();
//        }
//    }
//
//    /** Helpers to map results **/
//    private Vehicle mapRow(ResultSet rs) throws SQLException {
//        return new Vehicle(
//                rs.getString("VIN"),
//                rs.getInt("year"),
//                rs.getString("make"),
//                rs.getString("model"),
//                rs.getString("vehicletype"),
//                rs.getString("color"),
//                rs.getInt("odometer"),
//                rs.getDouble("price")
//        );
//    }
//
//
//    private List<Vehicle> mapList(ResultSet rs) throws SQLException {
//        List<Vehicle> list = new ArrayList<>();
//        while (rs.next()) list.add(mapRow(rs));
//        rs.close();
//        return list;
//    }
}
