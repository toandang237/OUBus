/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.User;
import com.dvt.utils.JdbcUtils;
import com.dvt.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author pc
 */
public class UserSevices {
    public List<User> getListUser() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM user");
            
            List<User> list = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), 
                            rs.getString("phone_number"), rs.getString("email"), 
                            rs.getString("username"), rs.getString("password"), 
                            rs.getBoolean("user_role"));
                list.add(user);
            }
            return list;
        }
    }
    
    public User getUserById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            String name = rs.getString("name");
//            String phone_number = rs.getString("phone_number");
//            String email = rs.getString("email");
//            String username = rs.getString("username");
//            String password = rs.getString("password");
//            boolean user_role = rs.getBoolean("user_role");
//            
//            return new User(id, name, phone_number, email, username, password, user_role

            List<User> list = this.getListUser();
            for (User u: list) {
                if (u.getId() == id) {
                    return u;
                }
            }
            return null;
        }
    }
    
    public User getUserByUsername(String username) throws SQLException {
        List<User> list = this.getListUser();
        
        for (User u: list) {
            if (u.getUsername().contains(username)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean checkAccount(String username, String password) {
        User user;
        try {
            user = this.getUserByUsername(username);
            if (user.getName() != null) {
//                Utils.getBox(DigestUtils.md5Hex(password).toUpperCase(), Alert.AlertType.INFORMATION).show();
                return (DigestUtils.md5Hex(password).toUpperCase()).contains(user.getPassword());
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSevices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}


