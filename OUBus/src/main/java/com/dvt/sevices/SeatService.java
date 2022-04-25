/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Seat;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class SeatService {
    public List<Seat> getListSeatByIdBus(int id_bus) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM seat WHERE id_bus = ?");
            stm.setInt(1, id_bus);
            ResultSet rs = stm.executeQuery();
            List<Seat> list = new ArrayList<>();
            while (rs.next()) {
                if (!rs.getBoolean("active")) {
                    list.add(new Seat(rs.getInt("id"), rs.getString("name"), rs.getInt("id_bus"), rs.getBoolean("active")));
                }
            }
            return list;
        }
    }
    
    public Seat getSeatById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM seat WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Seat(rs.getInt("id"), rs.getString("name"), rs.getInt("id_bus"), rs.getBoolean("active"));
            }
            return null;
        }
    }
    
    public boolean updateSeatStatus(int id, boolean b) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE seat SET active = ? WHERE id = ?");
            stm.setBoolean(1, b);
            stm.setInt(2, id);
            return stm.executeUpdate() > 0;
        }
    }
}
