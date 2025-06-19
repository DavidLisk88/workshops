package com.pluralsight.CarDealershipAPI.controllers;


import com.pluralsight.CarDealershipAPI.dao.JdbcDealershipDao;
import com.pluralsight.CarDealershipAPI.database.Database;
import com.pluralsight.CarDealershipAPI.model.Dealership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dealership")
public class DealershipController {

    private JdbcDealershipDao dealershipDao;

    public DealershipController(Database config) throws SQLException{
        this.dealershipDao = new JdbcDealershipDao(config);
    }

    @GetMapping
    public List<Dealership> getAllDealerships(@RequestParam (required = false) String name) throws SQLException{
        if (dealershipDao == null)
            return new ArrayList<>();

        List<Dealership> allDealerships = dealershipDao.getAllDealerships();
        List<Dealership> result = new ArrayList<>();

        for (Dealership dealership : allDealerships){
            if (name == null || dealership.getName().equalsIgnoreCase(name)){
                result.add(dealership);
            }
        }
        return result;
    }

    @PostMapping("/add")
    public Dealership addDealership(@RequestBody Dealership d) throws SQLException{
        System.out.println("Added new dealership: " + d);

        dealershipDao.addDealership(d);
        return d;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dealership> update(@PathVariable int id, @RequestBody Dealership dealership) {
        Dealership existing = dealershipDao.findById(id); // Get the current (old) data

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        dealershipDao.update(id, dealership); // Apply the new changes

        System.out.println("Updated: " + dealership);

        return ResponseEntity.ok(existing); // Return the OLD data that was updated
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Dealership> delete(@PathVariable int id, @RequestBody Dealership dealership){
        Dealership existing = dealershipDao.findById(id);

        if(existing == null){
            return ResponseEntity.notFound().build();
        }

        System.out.println("Deleted: " + id + "\n" + dealership);

        dealershipDao.delete(id, dealership);

        return ResponseEntity.ok(existing);
    }


}
