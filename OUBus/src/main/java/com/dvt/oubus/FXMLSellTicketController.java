/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Passenger;
import com.dvt.pojo.Seat;
import com.dvt.pojo.Ticket;
import com.dvt.pojo.Trip;
import com.dvt.pojo.User;
import com.dvt.sevices.PassengerService;
import com.dvt.sevices.SeatService;
import com.dvt.sevices.TicketService;
import com.dvt.sevices.TripService;
import com.dvt.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLSellTicketController implements Initializable {
    private static final TripService S_TRIP = new TripService();
    private static final SeatService S_SEAT = new SeatService();
    private static final TicketService S_TICKET = new TicketService();
    private static final PassengerService S_PASSENGER = new PassengerService();
    
    private User User;
    @FXML private DatePicker datePickerSearch;
    @FXML private DatePicker datePickerDate;
    @FXML private TableView<Trip> tableSellTicket;
    @FXML private TableColumn<Trip, Integer> columnID;
    @FXML private TableColumn<Trip, String> columnTripName;
    @FXML private TableColumn<Trip, String> columnBusName;
    @FXML private TableColumn<Trip, String> columnLicensePlates;
    @FXML private TableColumn<Trip, Integer> columnNumberOfSeats;
    @FXML private TableColumn<Trip, String> columnDate;
    @FXML private TableColumn<Trip, String> columnTime;
    @FXML private TextField txtTripName;
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
        try {
            // TODO
            this.loadColumns();
            this.loadData(null);
            this.loadTripFromTable();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSellTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        txtTripName.textProperty().addListener(event -> {
            try {
                this.loadData(txtTripName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSellTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        cbbSeats.valueProperty().addListener((obs, val1, val2) -> {
            if (val2 != null) {
                seat = val2;
            }
        });
    }
    
    public void loadColumns() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTripName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBusName.setCellValueFactory(new PropertyValueFactory<>("bus_name"));
        columnLicensePlates.setCellValueFactory(new PropertyValueFactory<>("license_plates"));
        columnNumberOfSeats.setCellValueFactory(new PropertyValueFactory<>("number_of_seats"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    
    public void loadData(String kw) throws SQLException {
        tableSellTicket.setItems(FXCollections.observableList(S_TRIP.getListTrip(kw)));
    }
    
    public void load() {
        lbId.setText(String.valueOf(User.getId()));
    }
    
    public void loadTripFromTable() {
        tableSellTicket.setRowFactory(r -> {
            TableRow<Trip> tr = new TableRow<>();
            tr.setOnMouseClicked(t -> {
                Trip trip = tableSellTicket.getSelectionModel().getSelectedItem();
                if (trip != null) {
                    textFieldTrip.setText(trip.toString());
                    try {
                        List<Seat> list = S_SEAT.getListSeatByIdBus(trip.getId_bus());
                        cbbSeats.setItems(FXCollections.observableList(list));
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLOrderTicketController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return  tr;
            
        });
    }
    
    public void sellAndPrintTicketHandler(ActionEvent event) throws SQLException, IOException {
        Trip trip = tableSellTicket.getSelectionModel().getSelectedItem();
        int staff_id = Integer.parseInt(lbId.getText());
        if (trip != null) {
            if (seat != null) {
                if (!"".equals(textFieldDeparture.getText()) && !"".equals(textFieldDestination.getText()) && !"".equals(textFieldPrice.getText()) && !"".equals(textFieldPhone.getText()) && !"".equals(textFieldEmail.getText())) {
                    String departure = textFieldDeparture.getText();
                    String destination = textFieldDestination.getText();
                    String fullName = textFieldFullName.getText();
                    String phone = textFieldPhone.getText();
                    String email = textFieldEmail.getText();
                    double price = Double.parseDouble(textFieldPrice.getText());
                    if (Utils.getBox("Ticket sales confirmation?", Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                        if (S_PASSENGER.addPassenger(new Passenger(fullName, email, phone))) {
                            List<Passenger> list = S_PASSENGER.getListPassenger();
                            Passenger p = list.get(list.size() - 1);
                            Ticket ticket = new Ticket(trip.getId(), p.getId(), staff_id, seat.getId(), departure, destination, price, true);
                            if (S_TICKET.addTicket(ticket) 
                                    && S_SEAT.updateSeatStatus(seat.getId(), true)) {
                                cbbSeats.setItems(FXCollections.observableList(S_SEAT.getListSeatByIdBus(tableSellTicket.getSelectionModel().getSelectedItem().getId_bus())));
                                Utils.getBox("Sale successful!", Alert.AlertType.INFORMATION).show();
                                FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLPrintTicket.fxml"));
                                stage = new Stage();
                                scene = new Scene(load.load());
                                FXMLPrintController controller = load.getController();
                                controller.setTicket(ticket);
                                controller.load();
                                stage.setScene(scene);
                                stage.show();
                            }
                            else {
                                Utils.getBox("Sale Failed!", Alert.AlertType.ERROR).show();
                            }
                        }
                        else {
                            Utils.getBox("Sale Failed!", Alert.AlertType.ERROR).show();
                        }
                    }
                }
            }
            else {
                Utils.getBox("Please choose a trip on trip table!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStaff.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Staff");
        FXMLStaffController controller = loader.getController();
        controller.setUser(User);
        controller.load();
        stage.setScene(scene);
        stage.show();
    }
    
    public void searchTripHandler(ActionEvent event) throws SQLException {
        List<Trip> list = S_TRIP.getListTrip(txtTripName.getText(), datePickerSearch.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tableSellTicket.setItems(FXCollections.observableList(list));
    }
    
    public void resetHandler(ActionEvent event) {
        textFieldTrip.setText("");
        datePickerDate.setValue(null);
        textFieldDeparture.setText("");
        textFieldDestination.setText("");
        textFieldEmail.setText("");
        textFieldFullName.setText("");
        textFieldPhone.setText("");
        textFieldPrice.setText("");
        textFieldTime.setText("");
    }
    
//    public

    /**
     * @param User the User to set
     */
    public void setUser(User User) {
        this.User = User;
    }
}
