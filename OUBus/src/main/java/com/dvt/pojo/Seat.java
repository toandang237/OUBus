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
    
    
    
}
