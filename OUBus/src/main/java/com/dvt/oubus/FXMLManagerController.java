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
import javafx.scene.control.ComboBox;
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
        col3.setPrefWidth(250);
        
        this.tableTrip.getColumns().addAll(col1, col2, col3);
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
        stage.setScene(scene);
        stage.show();
    }
    
    public void busManagermentHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBus.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    
    public void addTripHandler(ActionEvent event) throws SQLException {
        Bus bus = cbbIdBus.getSelectionModel().getSelectedItem();
        String name = txtName.getText();
        int id_bus = bus.getId();
        if (name.equals("") || bus == null) {
            Utils.getBox("Trip name and bus id cannot empty!!!", Alert.AlertType.WARNING).show();
        }
        else {
            Trip trip = new Trip(id_bus, name);
            if (S_TRIP.addTrip(trip)) {
                Utils.getBox("Add successfull!!!", Alert.AlertType.INFORMATION).show();
                loadData(null);
            }
            else {
                Utils.getBox("Add failed!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
}
