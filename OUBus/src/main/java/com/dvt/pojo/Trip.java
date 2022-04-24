/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;
import java.lang.String;

/**
 *
 * @author pc
 */
public class Trip {
    private int id;
    private int id_bus;
    private String name;
    private String bus_name;
    private String license_plates;
    private int number_of_seats;
    private String date;
    private String time;
    
    public Trip() {
    }

    public Trip(int id, int id_bus, String name, String date, String time) {
        this.id = id;
        this.id_bus = id_bus;
        this.name = name;
        this.date = date;
        this.time = time;
    }
    
    public Trip(int id_bus, String name, String date, String time) {
        this.id_bus = id_bus;
        this.name = name;
        this.date = date;
        this.time = time;
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
     * @return the id_bus
     */
    public int getId_bus() {
        return id_bus;
    }

    /**
     * @param id_bus the id_bus to set
     */
    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Strip id: " + id + " - Strip name:" + name + " (" + date + " " + time + ")"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * @return the bus_name
     */
    public String getBus_name() {
        return bus_name;
    }

    /**
     * @param bus_name the bus_name to set
     */
    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    /**
     * @return the license_plates
     */
    public String getLicense_plates() {
        return license_plates;
    }

    /**
     * @param license_plates the license_plates to set
     */
    public void setLicense_plates(String license_plates) {
        this.license_plates = license_plates;
    }

    /**
     * @return the number_of_seats
     */
    public int getNumber_of_seats() {
        return number_of_seats;
    }

    /**
     * @param number_of_seats the number_of_seats to set
     */
    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
}
