/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.pojo.Trip;
import com.dvt.sevices.TripService;
import com.dvt.utils.JdbcUtils;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 *
 * @author DELL
 */
public class TripServiceTest {
    private static Connection conn;
    private static TripService t;
    
    @BeforeAll
    public static void beforeAll() {
        try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(TripServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        t = new TripService();
    }
    
    @AfterAll
    public static void afterAll() {
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException ex){
                Logger.getLogger(TripServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void getListTrip() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM Trip");
        String nameTrip = "SG-NT";
        List<String> kq = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("name");
            kq.add(name);
        }
        
        boolean b = kq.contains(nameTrip);
        Assertions.assertTrue(b);
    }
    
    @Test
    public void getTripById() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM Trip");

        List <String> kq = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString("id");
            kq.add(id);
        }

        Set <String> kq2 = new HashSet<>(kq);

        Assertions.assertEquals(kq.size(), kq2.size());
    }
    
    @Test
    public void addTripSuccessful() throws SQLException, ParseException {
        
        Trip tr = new Trip(10, "SG-LD", "2022-04-28", "11:15");
        Assertions.assertTrue(t.addTrip(tr));
        
        List<Trip> trip = t.getListTrip(tr.getName());
        
        Assertions.assertEquals(tr.getName(), trip.get(trip.size() - 1).getName());
    }
}
