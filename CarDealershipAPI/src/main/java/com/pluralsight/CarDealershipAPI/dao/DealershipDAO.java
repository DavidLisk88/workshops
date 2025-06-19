// DealershipDAO.java
package com.pluralsight.CarDealershipAPI.dao;

import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.Dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DealershipDAO {


    // This method inserts and sets the generated ID on the object.
    void addDealership(Dealership d) throws SQLException;


    // This method returns all dealerships in the database
    List<Dealership> getAllDealerships() throws SQLException;

    void update(int id, Dealership dealership);

    void delete(int id, Dealership dealership);

    Dealership findById(int id);

}

