/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.utils.JdbcUtils;
import com.dvt.pojo.Bus;
import com.dvt.sevices.BusService;
import com.dvt.sevices.BusService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
public class BusServiceTest {

    private static Connection conn;
    private static BusService s;

    @BeforeAll
    public static void beforeAll() {
        try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(BusServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        s = new BusService();   
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

//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
    @Test
    public void testGetNameUnique() throws Exception {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM bus");

        List <String> kq = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("name");
            kq.add(name);
        }

        Set <String> kq2 = new HashSet<>(kq);

        Assertions.assertEquals(kq.size(), kq2.size());
    }
    @Test
    public void testGet() throws Exception {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM bus");

        List <String> kq = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("name");
            kq.add(name);
        }

        Set <String> kq2 = new HashSet<>(kq);

        Assertions.assertEquals(kq.size(), kq2.size());
    }
    
    @Test
    public void testSearchSuccessful() throws SQLException {
        String kw = "TOAN";
        List<Bus> buses = s.getListBus(kw);
        for(Bus b:buses){
            Assertions.assertTrue(b.getName().toLowerCase().contains(kw.toLowerCase()));
        }
    }
//
    @Test
    public void testGetBusById() throws Exception {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM bus");

        List <String> kq = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString("id");
            kq.add(id);
        }

        Set <String> kq2 = new HashSet<>(kq);

        Assertions.assertEquals(kq.size(), kq2.size());
    }

    @Test
    public void testDeleteBus() throws Exception {
        System.out.println("deleteBus");
        int id = 2;
        Assertions.assertFalse(s.deleteBus(id));
        Assertions.assertNull(s.getBusById(id));
    }

}