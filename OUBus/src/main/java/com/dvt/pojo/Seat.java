/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;

/**
 *
 * @author pc
 */
public class Seat {
    private int id;
    private String name;
    private int id_bus;
    private boolean active;//0: have people, 1: haven't

    public Seat() {
    }

    public Seat(int id, String name, int id_bus, boolean active) {
        this.id = id;
        this.name = name;
        this.id_bus = id_bus;
        this.active = active;
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + " (seat id: " + id + " )"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
