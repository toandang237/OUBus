/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Ticket {
    private int id;
    private int trip_id;
    private int passenger_id;
    private int staff_id;
    private int seat_id;
    private String departure;
    private String destination;
    private double price;
    private String passenger_name;
    private String trip_name;
    private String seat;
    private String staff_name;
    private boolean status;
    
    public Ticket() {
    }

    public Ticket(int id, int trip_id, int passenger_id, int staff_id, int seat_id, String departure, String destination, double price, boolean status) {
        this.id = id;
        this.trip_id = trip_id;
        this.passenger_id = passenger_id;
        this.staff_id = staff_id;
        this.seat_id = seat_id;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.status = status;
    }
    
    public Ticket(int trip_id, int passenger_id, int staff_id, int seat_id, String departure, String destination, double price, boolean status) {
        this.trip_id = trip_id;
        this.passenger_id = passenger_id;
        this.staff_id = staff_id;
        this.seat_id = seat_id;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.status = status;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the trip_id
     */
    public int getTrip_id() {
        return trip_id;
    }

    /**
     * @param trip_id the trip_id to set
     */
    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    /**
     * @return the passenger_id
     */
    public int getPassenger_id() {
        return passenger_id;
    }

    /**
     * @param passenger_id the passenger_id to set
     */
    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    /**
     * @return the staff_id
     */
    public int getStaff_id() {
        return staff_id;
    }

    /**
     * @param staff_id the staff_id to set
     */
    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    /**
     * @return the seat_id
     */
    public int getSeat_id() {
        return seat_id;
    }

    /**
     * @param seat_id the seat_id to set
     */
    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the passenger_name
     */
    public String getPassenger_name() {
        return passenger_name;
    }

    /**
     * @param passenger_name the passenger_name to set
     */
    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    /**
     * @return the trip_name
     */
    public String getTrip_name() {
        return trip_name;
    }

    /**
     * @param trip_name the trip_name to set
     */
    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    /**
     * @return the seat
     */
    public String getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

    /**
     * @return the staff_name
     */
    public String getStaff_name() {
        return staff_name;
    }

    /**
     * @param staff_name the staff_name to set
     */
    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    @Override
    public String toString() {
        return "Passenger id: " + passenger_id + " - Passenger name: " + passenger_name + " - Trip name: " + trip_name; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
