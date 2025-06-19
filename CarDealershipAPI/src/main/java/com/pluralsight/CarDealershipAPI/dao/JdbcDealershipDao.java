package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.Dealership;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDealershipDao implements DealershipDAO {

    // Create a connection tot the database
    private final Connection connection;

    public JdbcDealershipDao(Database config) throws SQLException{
        this.connection = config.getConnection();
    }


    @Override
    public void addDealership(Dealership d) throws SQLException {
        String sql = "INSERT INTO dealerships(name, address, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

    @Override
    public List<Dealership> getAllDealerships() throws SQLException {
        List<Dealership> list = new ArrayList<>();
        String sql = "SELECT dealership_id, name, address, phone FROM dealerships";
        try (PreparedStatement ps = connection.prepareStatement(sql);
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

    @Override
    public void update (int id, Dealership dealership){

        String sql = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE dealership_id = ?" ;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, dealership.getName());
            statement.setString(2, dealership.getAddress());
            statement.setString(3, dealership.getPhone());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public void delete(int id, Dealership dealership){

        String sql = "DELETE FROM dealerships WHERE dealership_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Dealership findById(int id){

        String sql = "SELECT * FROM dealerships WHERE dealership_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Dealership dealership = new Dealership();
                dealership.setDealershipId(id);
                dealership.setName(resultSet.getString("name"));
                dealership.setAddress(resultSet.getString("address"));
                dealership.setPhone(resultSet.getString("phone"));
                return dealership;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
