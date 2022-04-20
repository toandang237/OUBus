/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;

/**
 *
 * @author pc
 */
public class Trip {
    private int id;
    private int id_bus;
    private String name;

    public Trip() {
    }

    public Trip(int id, int id_bus, String name) {
        this.id = id;
        this.id_bus = id_bus;
        this.name = name;
    }
    
    public Trip(int id_bus, String name) {
        this.id_bus = id_bus;
        this.name = name;
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
    
    
}
