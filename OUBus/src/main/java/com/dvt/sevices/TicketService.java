/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Ticket;
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
public class TicketService {
    private static final SeatService S_SEAT = new SeatService();
    private static final PassengerService S_PASSENGER = new PassengerService();
    private static final TripService S_TRIP = new TripService();
    private static final UserSevices S_User = new UserSevices();
    
    public List<Ticket> getListTicket() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM ticket");
            ResultSet rs = stm.executeQuery();
            List<Ticket> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int trip_id = rs.getInt("trip_id");
                int passenger_id = rs.getInt("passenger_id");
                int staff_id = rs.getInt("staff_id");
                int seat_id = rs.getInt("seat_id");
                String depature = rs.getString("departure");
                String destination = rs.getString("destination");
                double price = rs.getDouble("price");
                boolean status = rs.getBoolean("status");
                Ticket ticket = new Ticket(id, trip_id, passenger_id, staff_id, seat_id, depature, destination, price, status);
                ticket.setPassenger_name(S_PASSENGER.getPassengerById(passenger_id).getName());
                ticket.setStaff_name(S_User.getUserById(staff_id).getName());
                ticket.setTrip_name(S_TRIP.getTripById(trip_id).getName());
                ticket.setSeat(S_SEAT.getSeatById(seat_id).getName());
                list.add(ticket);
            }
            return list;
        }
    }
    
    public boolean addTicket(Ticket t) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO ticket(trip_id, passenger_id, staff_id, seat_id, departure, destination, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, t.getTrip_id());
            stm.setInt(2, t.getPassenger_id());
            stm.setInt(3, t.getStaff_id());
            stm.setInt(4, t.getSeat_id());
            stm.setString(5, t.getDeparture());
            stm.setString(6, t.getDestination());
            stm.setDouble(7, t.getPrice());
            stm.setBoolean(8, false);
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean updateTicketStatus(int id, boolean status) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE ticket SET status = ? WHERE id = ?");
            stm.setBoolean(1, status);
            stm.setInt(2, id);
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean deleteTicket(int id, int passenger_id, int seat_id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm1 = conn.prepareStatement("DELETE FROM ticket WHERE id = ?");
            PreparedStatement stm2 = conn.prepareStatement("DELETE FROM passenger WHERE id = ?");
            PreparedStatement stm3 = conn.prepareStatement("UPDATE seat SET active = ? WHERE id = ?");
            stm1.setInt(1, id);
            stm2.setInt(1, passenger_id);
            if (stm1.executeUpdate() > 0 && stm2.executeUpdate() > 0 && stm3.executeUpdate() > 0) {
                conn.commit();
                return true;
            }
            return false;
        }
    }
}
