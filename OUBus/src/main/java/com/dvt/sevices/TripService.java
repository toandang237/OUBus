/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Trip;
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

/**
 *
 * @author pc
 */
public class TripService {
    public List<Trip> getListTrip(String kw) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM trip WHERE name like concat('%', ?, '%')");
            if (kw == null) {
                kw = "";
            }
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            List<Trip> list = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("id");
                int id_bus = rs.getInt("id_bus");
                String name = rs.getString("name");
                list.add(new Trip(id, id_bus, name));
            }
            return list;
        }
    }
    
    public boolean addTrip(Trip trip) {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "INSERT INTO trip(id_bus, name) VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, trip.getId_bus());
            stm.setString(2, trip.getName());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TripService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteTrip(int id) {
        try (Connection conn = Jdb)
    }
}
