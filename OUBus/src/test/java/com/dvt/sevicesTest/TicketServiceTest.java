/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.sevices.PassengerService;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author DELL
 */
public class TicketServiceTest {
    private static Connection conn;
    private static TicketServiceTest s;
    
    @BeforeAll
    public static void beforeAll() throws SQLException {
       try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(BusServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        s = new TicketServiceTest();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TicketServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}