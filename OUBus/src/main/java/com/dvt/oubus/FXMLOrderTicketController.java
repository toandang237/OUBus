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
public class FXMLOrderTicketController implements Initializable {
    private static final TripService S_TRIP = new TripService();
    private static final SeatService S_SEAT = new SeatService();
    private static final TicketService S_TICKET = new TicketService();
    private static final PassengerService S_PASSENGER = new PassengerService();
    private User User;
    //controller
    @FXML private DatePicker datePickerSearch;
    @FXML private TableView<Trip> tableTrip;
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
            Logger.getLogger(FXMLOrderTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        tableTrip.setItems(FXCollections.observableList(S_TRIP.getListTrip(kw)));
    }
    
    public void load() {
        lbId.setText(String.valueOf(User.getId()));
    }
    
    public void loadTripFromTable() {
        tableTrip.setRowFactory(r -> {
            TableRow<Trip> tr = new TableRow<>();
            tr.setOnMouseClicked(t -> {
                Trip trip = tableTrip.getSelectionModel().getSelectedItem();
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
    
    
    public void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStaff.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        FXMLStaffController controller = loader.getController();
        controller.setUser(User);
        controller.load();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Staff");
        stage.setScene(scene);
        stage.show();
    }
    
    public void searchTripHandler(ActionEvent event) throws SQLException {
        List<Trip> list = S_TRIP.getListTrip(txtTripName.getText(), datePickerSearch.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tableTrip.setItems(FXCollections.observableList(list));
    }
    
    public void resetHandler(ActionEvent event) {
        textFieldTrip.setText("");
        textFieldDeparture.setText("");
        textFieldDestination.setText("");
        textFieldEmail.setText("");
        textFieldFullName.setText("");
        textFieldPhone.setText("");
        textFieldPrice.setText("");
    }
    
    /**
     *
     * @param event
     * @throws SQLException
     */
    public void bookHandler(ActionEvent event) throws SQLException {
        Trip trip = tableTrip.getSelectionModel().getSelectedItem();
//        String id = cbbSeats.getPromptText().split(" ")[4];
//        int seat_id = Integer.parseInt(id);
//        Seat seat = S_SEAT.getSeatById(seat_id);
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
                    if (Utils.getBox("Ticket book confirmation?", Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                        if (S_PASSENGER.addPassenger(new Passenger(fullName, email, phone))) {
                            List<Passenger> list = S_PASSENGER.getListPassenger();
                            Passenger p = list.get(list.size() - 1);
                            if (S_TICKET.addTicket(new Ticket(trip.getId(), p.getId(), staff_id, seat.getId(), departure, destination, price, false)) 
                                    && S_SEAT.updateSeatStatus(seat.getId(), true)) {
                                cbbSeats.setItems(FXCollections.observableList(S_SEAT.getListSeatByIdBus(tableTrip.getSelectionModel().getSelectedItem().getId_bus())));
                                Utils.getBox("Book successful!", Alert.AlertType.INFORMATION).show();
                            }
                            else {
                                Utils.getBox("Book Failed!", Alert.AlertType.ERROR).show();
                            }
                        }
                        else {
                            Utils.getBox("Book Failed!", Alert.AlertType.ERROR).show();
                        }
                    }
                }
                else {
                    Utils.getBox("Please enter full ticket selling information and customer information!", Alert.AlertType.WARNING).show();
                }
            }
            else {
                Utils.getBox("Please choose a seat!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("Please choose a trip on trip table!", Alert.AlertType.WARNING).show();
        }
    }

    /**
     * @param User the User to set
     */
    public void setUser(User User) {
        this.User = User;
    }
}
