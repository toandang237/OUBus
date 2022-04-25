/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Passenger;
import com.dvt.sevices.PassengerService;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author ADMIN
 */
public class PassengerServiceTest {
    private static Connection conn;
    private static PassengerService ps;
    
    @BeforeAll
    public static void beforeAll() throws SQLException {
       try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(PassengerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ps = new PassengerService();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PassengerServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
  
    /**
     * Test of getPassengerById method, of class PassengerService.
     */
    @Test
    public void testGetPassengerById() throws Exception {
//        System.out.println("getPassengerById");
//        String sdt = "0356665434";
//        String kq = "";
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM passenger");
//        List <String> kq = new ArrayList<>();
//
//        while(rs.next()){
//           String kq1 = rs.getString("phone_number");
//           System.out.println(rs.getString("email"));
//           kq.add(kq1);      
//        }
//
//        Assertions.assertEquals(sdt, kq);
        
        PassengerService pass = new PassengerService();
        int id1 = 2;
        //Passenger expResult = new Passenger(4, "vu", "1951052242vu@ou.edu", "0354897977");
        Passenger p = pass.getPassengerById(1);
        System.out.println(p.getId());
        System.out.println(p.getEmail());
        Assertions.assertEquals(p.getId(), id1);
        
    }
    
}