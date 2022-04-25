/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.pojo.Seat;
import com.dvt.sevices.SeatService;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DELL
 */
public class SeatServiceTest {
    private static Connection conn;
    private static SeatService s;
    
    public SeatServiceTest() {
    }
    
    @BeforeAll
    public static void beforeAll() {
        try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(BusServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        s = new SeatService();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BusServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

    /**
     * Test of getListSeatByIdBus method, of class SeatService.
     */
    @Test
    public void testGetListSeatByIdBus() throws Exception {
        System.out.println("getListSeatByIdBus");
        int id_bus = 0;
        SeatService instance = new SeatService();
        List<Seat> expResult = null;
        List<Seat> result = instance.getListSeatByIdBus(id_bus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeatById method, of class SeatService.
     */
    @Test
    public void testGetSeatById() throws Exception {
        System.out.println("getSeatById");
        int id = 0;
        SeatService instance = new SeatService();
        Seat expResult = null;
        Seat result = instance.getSeatById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
