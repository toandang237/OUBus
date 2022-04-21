/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.sevices;

import com.dvt.pojo.Ticket;
import com.dvt.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
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
                Date time = rs.getDate("time");
                double price = rs.getDouble("price");
                Ticket ticket = new Ticket(id, trip_id, passenger_id, staff_id, seat_id, depature, destination, time, price);
                ticket.setPassenger_name(S_PASSENGER.getPassengerById(passenger_id).getName());
                ticket.setStaff_name(S_User.getUserById(staff_id).getName());
                ticket.setTrip_name(S_TRIP.getTripById(trip_id).getName());
                ticket.setSeat(S_SEAT.getSeatById(seat_id).getName());
                list.add(ticket);
            }
            return list;
        }
    }
}
