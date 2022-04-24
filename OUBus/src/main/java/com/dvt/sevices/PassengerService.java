/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Passenger;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class PassengerService {
    public Passenger getPassengerById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM passenger WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Passenger(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number"));
            }
            return null;
        }
    }
    
    public boolean addPassenger(Passenger p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO passenger(name, email, phone_number) VALUES (?, ?, ?)");
            stm.setString(1, p.getName());
            stm.setString(2, p.getEmail());
            stm.setString(3, p.getPhone_number());
            return stm.executeUpdate() > 0;
        }
    }
    
    public List<Passenger> getListPassenger() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM passenger");
            ResultSet rs = stm.executeQuery();
            List<Passenger> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Passenger(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone_number")));
            }
            return list;
        }
    }
}
