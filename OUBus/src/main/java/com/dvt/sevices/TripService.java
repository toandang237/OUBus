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
}
