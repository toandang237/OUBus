/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Bus;
import com.dvt.pojo.Trip;
import com.dvt.sevices.BusService;
import com.dvt.sevices.TripService;
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
import javafx.scene.control.ComboBox;
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
public class FXMLManagerController implements Initializable {
    private static final TripService S_TRIP = new TripService();
    private static final BusService S_BUS = new BusService();
    
    @FXML private TableView<Trip> tableTrip;
    @FXML private TextField txtSearch;
    @FXML private TextField txtIdStrip;
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
//            this.loadDataFromTableView();
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
    
    public void serachTripHandler(ActionEvent event) {
        String keyWord = this.txtSearch.getText();
        try {
            this.loadData(keyWord);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDataFromTableView() throws SQLException {
        Trip trip = tableTrip.getSelectionModel().getSelectedItem();
        if (trip != null) {
            txtIdStrip.setText(String.valueOf(trip.getId()));
            cbbIdBus.setPromptText(S_BUS.getBusById(trip.getId_bus()).toString());
            txtName.setText(trip.getName());
        }
    }
    
    public void loadComboxBox() throws SQLException {
        cbbIdBus.setItems(FXCollections.observableList(S_BUS.getListBus()));
    }
}
