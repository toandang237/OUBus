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
                list.add(new Seat(rs.getInt("id"), rs.getString("name"), rs.getInt("id_bus"), rs.getBoolean("active")));
            }
            return list;
        }
    }
    
    public Seat getSeatById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Seat WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Seat(rs.getInt("id"), rs.getString("name"), rs.getInt("id_bus"), rs.getBoolean("active"));
            }
            return null;
        }
    }
}
