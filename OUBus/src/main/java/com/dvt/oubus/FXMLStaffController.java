/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Ticket;
import com.dvt.pojo.User;
import com.dvt.sevices.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLStaffController implements Initializable {
    private static final TicketService S_TICKET = new TicketService();
    private User user;
    
    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private TableView<Ticket> tableTickets;
    @FXML private TableColumn<Ticket, Integer> columnTicketID;
    @FXML private TableColumn<Ticket, String> columnPassengerName;
    @FXML private TableColumn<Ticket, String> columnTripName;
    @FXML private TableColumn<Ticket, String> columnSeat;
    @FXML private TableColumn<Ticket, String> columnStaffName;
    @FXML private TableColumn<Ticket, String> columnDeparture;
    @FXML private TableColumn<Ticket, String> columnDestination;
    @FXML private TableColumn<Ticket, Double> columnPrice;
    @FXML private TextField txtPassengerName;
    
    @FXML private Label lbUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadColumn();
            this.loadData("");
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
        columnDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
    
    public void sellTicketHandler(ActionEvent event) throws IOException {
        FXMLLoader sellTicketLoader = new FXMLLoader(getClass().getResource("FXMLSellTickets.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(sellTicketLoader.load());
        FXMLSellTicketController controller = sellTicketLoader.getController();
        controller.setUser(user);
        controller.load();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    
    public void logoutHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    
    public void bookTicketHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLOrderTicket.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        FXMLOrderTicketController controller = loader.getController();
        controller.setUser(user);
        controller.load();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    
    public void changeAndCancelHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLChangeAndCancelTicket.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        FXMLChangeAndCancelTicketController controller = loader.getController();
        controller.setUser(user);
        controller.load();
        stage.setScene(scene);
        stage.show();
    }
     
    public void setUser(User user) {
        this.user = user;
    }
    
}
