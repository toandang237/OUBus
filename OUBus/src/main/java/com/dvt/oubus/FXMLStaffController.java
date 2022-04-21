/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Ticket;
import com.dvt.pojo.User;
import com.dvt.sevices.TicketService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLStaffController implements Initializable {
    private static final TicketService S_TICKET = new TicketService();
    private User user;
    
    @FXML private TableView<Ticket> tableTickets;
    @FXML private TableColumn<Ticket, Integer> columnTicketID;
    @FXML private TableColumn<Ticket, String> columnPassengerName;
    @FXML private TableColumn<Ticket, String> columnTripName;
    @FXML private TableColumn<Ticket, String> columnSeat;
    @FXML private TableColumn<Ticket, String> columnStaffName;
    @FXML private TableColumn<Ticket, String> columnDeparture;
    @FXML private TableColumn<Ticket, String> columnDestination;
    @FXML private TableColumn<Ticket, Date> columnDate;
    @FXML private TableColumn<Ticket, Double> price;
    @FXML private TextField txtPassengerName;
    
    @FXML private Label lbUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadColumn();
            this.loadData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtPassengerName.textProperty().addListener(event -> {
            try {
                this.loadData(txtPassengerName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLStaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    public void load() {
        lbUser.setText(user.getName());
    }
    
    public void loadColumn() throws SQLException {
        columnTicketID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPassengerName.setCellValueFactory(new PropertyValueFactory<>("passenger_name"));
        columnTripName.setCellValueFactory(new PropertyValueFactory<>("trip_name"));
        columnSeat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        columnStaffName.setCellValueFactory(new PropertyValueFactory<>("staff_name"));
        columnDeparture.setCellValueFactory(new PropertyValueFactory<>("daparture"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
    }
    
    public void loadData(String kw) throws SQLException {
        List<Ticket> list = new ArrayList<>();
        for (Ticket ticket: S_TICKET.getListTicket()) {
            if (ticket.getPassenger_name().toUpperCase().contains(kw.toUpperCase())) {
                list.add(ticket);
            }
        }
        tableTickets.setItems(FXCollections.observableList(list));
    }
    
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
