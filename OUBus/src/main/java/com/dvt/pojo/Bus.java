/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;

/**
 *
 * @author pc
 */
public class Bus {
    private int id;
    private String name;
    private String license_plate;
    private int number_of_seats;

    public Bus() {
    }

    public Bus(int id, String name, String license_plate, int number_of_seats) {
        this.id = id;
        this.name = name;
        this.license_plate = license_plate;
        this.number_of_seats = number_of_seats;
    }
    
    public Bus(String name, String license_plate, int number_of_seats) {
        this.name = name;
        this.license_plate = license_plate;
        this.number_of_seats = number_of_seats;
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

    /**
     * @return the license_plate
     */
    public String getLicense_plate() {
        return license_plate;
    }

    /**
     * @param license_plate the license_plate to set
     */
    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
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

    @Override
    public String toString() {
        return "Bus id: " + this.id + " - Bus name: " + this.name + " - License plates: " + this.license_plate + " - Number of seats: " + this.number_of_seats; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
