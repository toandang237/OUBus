/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Bus;
import com.dvt.pojo.Trip;
import com.dvt.sevices.BusService;
import com.dvt.sevices.TripService;
import com.dvt.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FXMLManagerController implements Initializable {
    private static final TripService S_TRIP = new TripService();
    private static final BusService S_BUS = new BusService();
    
    @FXML private TableView<Trip> tableTrip;
    @FXML private TextField txtSearch;
    @FXML private Label lbIdTrip;
    @FXML private ComboBox<Bus> cbbIdBus;
    @FXML private TextField txtName;
    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtTime;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        try {
            this.loadData(null);
            this.loadComboxBox();
            this.loadDataFromTableView();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//    public void addTripHandler(ActionEvent event) {
//        
//    }
    
    @SuppressWarnings("unchecked")
    private void loadColumns() {
        TableColumn col1 = new TableColumn("ID TRIP");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(30);
        TableColumn col2 = new TableColumn("ID BUS");
        col2.setCellValueFactory(new PropertyValueFactory("id_bus"));
        col2.setPrefWidth(30);
        TableColumn col3 = new TableColumn("NAME TRIP");
        col3.setCellValueFactory(new PropertyValueFactory("name"));
        col3.setPrefWidth(150);
        TableColumn col4 = new TableColumn("DATE");
        col4.setCellValueFactory(new PropertyValueFactory("date"));
        col4.setPrefWidth(50);
        TableColumn col5 = new TableColumn("TIME");
        col5.setCellValueFactory(new PropertyValueFactory("time"));
        col5.setPrefWidth(50);
        
        this.tableTrip.getColumns().addAll(col1, col2, col3, col4, col5);
    }
    
    public void loadData(String kw) throws SQLException {
        this.tableTrip.setItems(FXCollections.observableList(S_TRIP.getListTrip(kw)));
    }
    
    public void loadDataFromTableView() throws SQLException {
        tableTrip.setRowFactory(r -> {
            TableRow<Trip> tr = new TableRow<>();
            tr.setOnMouseClicked(t -> {
                Trip trip = tableTrip.getSelectionModel().getSelectedItem();
                if (trip != null) {
                        try {
                            Bus bus = S_BUS.getBusById(trip.getId_bus());
                            cbbIdBus.setPromptText(bus.toString());
                            lbIdTrip.setText(String.valueOf(trip.getId()));
                            txtName.setText(trip.getName());
                            datePicker.setValue(LocalDate.parse(trip.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                            txtTime.setText(trip.getTime());
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLManagerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            });
            return  tr;
            
        });
                
    }
    
    public void loadComboxBox() throws SQLException {
        cbbIdBus.setItems(FXCollections.observableList(S_BUS.getListBus(null)));
    }
    
    
    public void serachTripHandler(ActionEvent event) {
        String keyWord = this.txtSearch.getText();
        try {
            this.loadData(keyWord);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void busManagermentHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    
    public void addTripHandler(ActionEvent event) throws SQLException, ParseException {
        Bus bus = cbbIdBus.getSelectionModel().getSelectedItem();
        String name = txtName.getText();
        String time = txtTime.getText();
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (name.equals("") || bus == null || time.equals("") || date.equals("")) {
            Utils.getBox("Please enter full information!!!", Alert.AlertType.WARNING).show();
        }
        else {
            int id_bus = bus.getId();
            Trip trip = new Trip(id_bus, name, date, time);
            if (S_TRIP.addTrip(trip)) {
                Utils.getBox("Add successfull!!!", Alert.AlertType.INFORMATION).show();
                loadData(null);
                txtName.setText("");
                txtTime.setText("");
                datePicker.setValue(null);
                cbbIdBus.getSelectionModel().clearSelection();
            }
            else {
                Utils.getBox("Add failed!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void deleteTripHandler(ActionEvent event) throws SQLException {
        Trip trip = tableTrip.getSelectionModel().getSelectedItem();
        if (trip != null) {
            if (Utils.getBox("Are you sure want to delete trip have id = " + trip.getId(), Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                if (S_TRIP.deleteTrip(trip.getId())) {
                    Utils.getBox("Delete successful!", Alert.AlertType.INFORMATION).show();
                    this.loadData(null);
                }
                else {
                    Utils.getBox("Delete failed!", Alert.AlertType.WARNING).show();
                }
            }
        }
        else {
            Utils.getBox("Select the trip to delete from table trip!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void updateTripHandler(ActionEvent event) throws SQLException, ParseException {
        Trip trip = tableTrip.getSelectionModel().getSelectedItem();
        if (trip != null) {
            Bus bus = cbbIdBus.getSelectionModel().getSelectedItem();
            String time = txtTime.getText();
            if (bus != null || !time.equals("") || datePicker.getValue() != null) {
                String name = txtName.getText();
                int id_bus = bus.getId();
                if (Utils.getBox("Are you sure want to update trip have id = " + trip.getId(), Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                    if (S_TRIP.updateTrip(trip.getId(), id_bus, name, datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) , time)) {
                        Utils.getBox("Update successful!", Alert.AlertType.INFORMATION).show();
                        this.loadData(null);
                    }
                    else {
                        Utils.getBox("Update failed!", Alert.AlertType.WARNING).show();
                    }
                }
            }
            else {
                Utils.getBox("Please enter full information!!!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("Please select the trip from table trip!", Alert.AlertType.WARNING).show();
        }
    }
}
