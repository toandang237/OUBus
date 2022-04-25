/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevicesTest;

import com.dvt.pojo.User;
import com.dvt.sevices.UserSevices;
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
 * @author DELL
 */
public class UserServiceTest {
    private static Connection conn;
    private static UserSevices us;
    
    @BeforeAll
    public static void beforeAll() {
        try{
            conn = JdbcUtils.getConn();
        }catch(SQLException ex){
            Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        us = new UserSevices();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testGetUserByUsername() throws Exception {
//        System.out.println("getUserByUsername");
//        UserSevices us = new UserSevices();
//        //List<User> list = new ArrayList<User>();  
//        User u = us.getUserByUsername("vu"); 
//        System.out.println(u.getName());
//        System.out.println(u.getEmail());
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM user");
        String username = "toan";
        List<String> list = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("name");
            list.add(name);    
        }
        boolean b = list.contains(username);
        
        Assertions.assertTrue(b);
    }
    
    
    
    
}
