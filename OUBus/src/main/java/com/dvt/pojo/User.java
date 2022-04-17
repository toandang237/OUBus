/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvt.pojo;

/**
 *
 * @author pc
 */
public class User {
    private int id;
    private String name;
    private String phone_number;
    private String email;
    private String username;
    private String password;
    private boolean userRole;//false: manager true: staff

    public User() {
    }

    public User(int id, String name, String phone_number, String email, String username, String password, boolean userRole) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    
    public User(String name, String phone_number, String email, String username, String password, boolean userRole) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
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
     * @return the phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * @param phone_number the phone_number to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userRole
     */
    public boolean getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(boolean userRole) {
        this.userRole = userRole;
    }
    
    
}
