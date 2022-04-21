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
    private String depature;
    private String destination;
    private Date date;
    private double price;
    private String passenger_name;
    private String trip_name;
    private String seat;
    private String staff_name;
    public Ticket() {
    }

    public Ticket(int id, int trip_id, int passenger_id, int staff_id, int seat_id, String depature, String destination, Date date, double price) {
        this.id = id;
        this.trip_id = trip_id;
        this.passenger_id = passenger_id;
        this.staff_id = staff_id;
        this.seat_id = seat_id;
        this.depature = depature;
        this.destination = destination;
        this.date = date;
        this.price = price;
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
     * @return the depature
     */
    public String getDepature() {
        return depature;
    }

    /**
     * @param depature the depature to set
     */
    public void setDepature(String depature) {
        this.depature = depature;
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
    
    
    
}
