/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Bus;
import com.dvt.pojo.Trip;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class TripService {
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public List<Trip> getListTrip(String kw) throws SQLException {
        BusService s = new BusService();
        
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
                String date = rs.getString("date");
                String time = rs.getString("time");
                Trip trip = new Trip(id, id_bus, name, date, time);
                Bus bus = s.getBusById(id_bus);
                trip.setBus_name(bus.getName());
                trip.setLicense_plates(bus.getLicense_plate());
                trip.setNumber_of_seats(bus.getNumber_of_seats());
                list.add(trip);
            }
            return list;
        }
    }
    
    public List<Trip> getListTrip(String kw, String date) throws SQLException {
        if (kw == null)
            kw = "";
        List<Trip> list = new ArrayList<>();
        for (Trip trip: getListTrip(kw)) {
            if (trip.getDate().equals(date)) {
                list.add(trip);
            }
        }
        return list;
    }
    
    public Trip getTripById(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM trip WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Trip(rs.getInt("id"), rs.getInt("id_bus"), rs.getString("name"), rs.getString("date"), rs.getString("time"));
            }
            return null;
        }
    }
    
    public boolean addTrip(Trip trip) throws ParseException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "INSERT INTO trip(id_bus, name, date, time) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, trip.getId_bus());
            stm.setString(2, trip.getName());
            stm.setString(3, trip.getDate());
            stm.setString(4, trip.getTime());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TripService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteTrip(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM trip WHERE id = ?");
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }
    
    @SuppressWarnings("deprecation")
    public boolean updateTrip(int id, int id_bus, String name, String date, String time) throws SQLException, ParseException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE trip SET name = ?, id_bus = ?, date = ?, time = ? WHERE id = ?");
            stm.setString(1, name);
            stm.setInt(2, id_bus);
            stm.setString(3, date);
            stm.setString(4, time);
            stm.setInt(5, id);
            
            return stm.executeUpdate() > 0;
        }
    }
}
