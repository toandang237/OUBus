/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Bus;
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
public class BusService {
    public List<Bus> getListBus() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM bus");
            ResultSet rs = stm.executeQuery();
            List<Bus> list = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String license_plates = rs.getString("license_plates");
                int number_of_seats = rs.getInt("number_of_seats");
                list.add(new Bus(id, name, license_plates, number_of_seats));
            }
            return list;
        }
    }
    
    public Bus getBusById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM bus WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Bus bus = null;
            
            if (rs.next()) {
                String name = rs.getString("name");
                String license_plates = rs.getString("license_plates");
                int number_of_seats = rs.getInt("number_of_seats");
                bus = new Bus(id, name, license_plates, number_of_seats);
            }
            
            return bus;
        }
    }
}
