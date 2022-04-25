/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Seat;
import com.dvt.pojo.Ticket;
import com.dvt.pojo.User;
import com.dvt.sevices.PassengerService;
import com.dvt.sevices.SeatService;
import com.dvt.sevices.TicketService;
import com.dvt.sevices.TripService;
import com.dvt.utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
public class FXMLChangeAndCancelTicketController implements Initializable {
    private static final TripService S_TRIP = new TripService();
    private static final SeatService S_SEAT = new SeatService();
    private static final TicketService S_TICKET = new TicketService();
    private static final PassengerService S_PASSENGER = new PassengerService();
    
    private User User;
    @FXML private TableView<Ticket> tableSellTicket;
    @FXML private TableColumn<Ticket, Integer> columnID;
    @FXML private TableColumn<Ticket, String> columnTripName;
    @FXML private TableColumn<Ticket, String> columnPassengerName;
    @FXML private TableColumn<Ticket, String> columnDeparture;
    @FXML private TableColumn<Ticket, String> columnDestination;
    @FXML private TableColumn<Ticket, Double> columnPrice;
    @FXML private TableColumn<Ticket, Boolean> columnStatus;
    @FXML private TextField txtPassengerName;
    @FXML private Label lbId;
    @FXML private TextField textFieldTrip;
    @FXML private ComboBox<Seat> cbbSeats;
    @FXML private TextField textFieldDeparture;
    @FXML private TextField textFieldDestination;
    @FXML private TextField textFieldTime;
    @FXML private TextField textFieldPrice;
    @FXML private TextField textFieldFullName;
    @FXML private TextField textFieldPhone;
    @FXML private TextField textFieldEmail;
    @FXML private Stage stage;
    @FXML private Scene scene;
    private Seat seat = null;
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        try {
            this.loadData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLChangeAndCancelTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        txtPassengerName.textProperty().addListener((o) -> {
            try {
                this.loadData(txtPassengerName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLChangeAndCancelTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void loadColumns() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPassengerName.setCellValueFactory(new PropertyValueFactory<>("passenger_name"));
        columnDeparture.setCellValueFactory(new PropertyValueFactory<>("depar"));
        columnTripName.setCellValueFactory(new PropertyValueFactory<>("trip_name"));
        columnDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnDeparture.getColumns().clear();
        
        TableColumn col3 = new TableColumn();
        col3.setCellFactory((p) -> {
            Button btn = new Button("Delete");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                Ticket ticket = (Ticket) c.getTableRow().getItem();
                
                if (Utils.getBox("Are you sure want to delete ticket!!!", Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                    try {
                        if (ticket != null) {
                            if (S_TICKET.deleteTicket(ticket.getId(), ticket.getPassenger_id(), ticket.getSeat_id()) == true) {
                                Utils.getBox("Delete successful!", Alert.AlertType.INFORMATION).show();
                                this.loadData(null);
                            } else {
                                Utils.getBox("Delete failed!", Alert.AlertType.ERROR).show();
                            }
                        }
                        else {
                            Utils.getBox("Cannot delete!!!", Alert.AlertType.ERROR).show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLChangeAndCancelTicketController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableSellTicket.getColumns().add(col3);
    }
    
    public void loadData(String kw) throws SQLException {
        if (kw == null) kw = "";
        List<Ticket> list = S_TICKET.getListTicket();
        List<Ticket> listTicket = new ArrayList<>();
        for (Ticket t: list) {
            if (t.getPassenger_name().contains(kw))
                listTicket.add(t);
        }
        tableSellTicket.setItems(FXCollections.observableList(listTicket));
    }
    
    public void load() {
        lbId.setText(String.valueOf(User.getId()));
    }
    
     public void setUser(User User) {
        this.User = User;
    }
}
