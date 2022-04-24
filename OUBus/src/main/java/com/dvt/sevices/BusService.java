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
    public List<Bus> getListBus(String kw) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            if (kw == null) {
                kw = "";
            }
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM bus WHERE name like concat('%', ?, '%')");
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            List<Bus> list = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String license_plate = rs.getString("license_plate");
                int number_of_seats = rs.getInt("number_of_seats");
                list.add(new Bus(id, name, license_plate, number_of_seats));
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
                String license_plate = rs.getString("license_plate");
                int number_of_seats = rs.getInt("number_of_seats");
                bus = new Bus(id, name, license_plate, number_of_seats);
            }
            
            return bus;
        }
    }
    
    public void addBus(Bus bus) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
//            conn.setAutoCommit(false);
            String sql = "INSERT INTO bus(name, license_plate, number_of_seats) VALUES (?, ?, ?)";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, bus.getName());
            stm.setString(2, bus.getLicense_plate());
            stm.setInt(3, bus.getNumber_of_seats());
            if (stm.executeUpdate() > 0) {
                for (int i = 0; i < bus.getNumber_of_seats(); i++) {
                    String sql1 = "INSERT INTO seat(name, id_bus, active) VALUES (?, ?, ?)";
                    PreparedStatement stm1 = conn.prepareStatement(sql1);
                    List<Bus> list = getListBus(null);
                    stm1.setString(1, "seat " + (i + 1));
                    stm1.setInt(2, list.get(list.size() - 1).getId());
                    stm1.setBoolean(3, false);
                    stm1.executeUpdate();
                }
            }
//            conn.commit();
            stm.close();
        }
    }
    
    public boolean deleteBus(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM bus WHERE id = ?");
            PreparedStatement stm1 = conn.prepareStatement("DELETE FROM seat WHERE id_bus = ?");
            stm.setInt(1, id);
            stm1.setInt(1, id);
            stm1.executeUpdate();
            return stm.executeUpdate() > 0;
        }
    }
}
