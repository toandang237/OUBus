/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.utils.JdbcUtils;
import com.dvt.pojo.Ticket;
import com.dvt.sevices.TicketService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
public class TicketServiceTest {
    private static Connection conn;
    private static TicketService ts;
    
    @BeforeAll
    public static void beforeAll() throws SQLException {
       try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(TicketServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        ts = new TicketService();
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
    @Test
    public void getListTicket() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM ticket");
        int seatid = 90;
        List<Integer> kq = new ArrayList<>();
        while(rs.next()){
            int id1 = rs.getInt("seat_id");
            System.out.println(id1);
//            String date = rs.getString("date");
            kq.add(id1);
        }
        boolean b = kq.contains(seatid);
        
        Assertions.assertTrue(b);
    }
    
    @Test
    public void addTicketSuccessful() throws SQLException, ParseException{
        Ticket t = new Ticket(14, 2, 1, 90, "Lâm Đồng ", "Đồng Nai", 200000, true);
        
        ts.addTicket(t);
        
    }
}