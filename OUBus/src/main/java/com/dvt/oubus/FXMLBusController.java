/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Bus;
import com.dvt.sevices.BusService;
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
import javafx.scene.control.ButtonType;
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
public class FXMLBusController implements Initializable {
    private static final BusService S = new BusService();
    
    @FXML private TableView<Bus> tableBus;
    @FXML private TextField txtSearch;
    @FXML private Label lbId;
    @FXML private TextField txtName;
    @FXML private TextField txtLicensePlate;
    @FXML private TextField txtNumberOfSeats;
    @FXML private Stage stage;
    @FXML private Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.loadColumns();
            this.loadData(null);
            this.loadDataFromTableView();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @SuppressWarnings("unchecked")
    public void loadColumns() {
        TableColumn col1 = new TableColumn("Bus id");
        col1.setCellValueFactory(new PropertyValueFactory("id"));
        col1.setPrefWidth(20);
        
        TableColumn col2 = new TableColumn("Name");
        col2.setCellValueFactory(new PropertyValueFactory("name"));
        col2.setPrefWidth(50);
        
        TableColumn col3 = new TableColumn("License plate");
        col3.setCellValueFactory(new PropertyValueFactory("license_plate"));
        col3.setPrefWidth(30);
        
        TableColumn col4 = new TableColumn("Number of seats");
        col4.setCellValueFactory(new PropertyValueFactory("number_of_seats"));
        col4.setPrefWidth(20);
        
        this.tableBus.getColumns().addAll(col1, col2, col3, col4);
    }
    
    public void loadData(String kw) throws SQLException {
        this.tableBus.setItems(FXCollections.observableList(S.getListBus(kw)));
    }
    
    public void loadDataFromTableView() {
        tableBus.setRowFactory(t -> {
            TableRow<Bus> tr = new TableRow<>();
            tr.setOnMouseClicked(event -> {
                Bus bus = tableBus.getSelectionModel().getSelectedItem();
                if (bus != null) {
                    lbId.setText(String.valueOf(bus.getId()));
                    txtName.setText(bus.getName());
                    txtLicensePlate.setText(bus.getLicense_plate());
                    txtNumberOfSeats.setText(String.valueOf(bus.getNumber_of_seats()));
                }
            });
            return tr;
        });
    }
    
    public void searchBusHandler(ActionEvent event) throws SQLException {
        String search = txtSearch.getText();
        loadData(search);
    }
    
    public void addBusHandler(ActionEvent event) {
        if (Utils.getBox("Are you sure want to add bus!", Alert.AlertType.CONFIRMATION).showAndWait().get()== ButtonType.OK) {
            if (!txtName.getText().equals("") && !txtLicensePlate.getText().equals("") && !txtNumberOfSeats.getText().equals("")) {
                try {
                    String name = txtName.getText();
                    String license_plate = txtLicensePlate.getText();
                    int num_Of_Seats = Integer.parseInt(txtNumberOfSeats.getText());
                    Bus bus = new Bus(name, license_plate, num_Of_Seats);
                    S.addBus(bus);
                    Utils.getBox("Add successful!", Alert.AlertType.INFORMATION).show();
                    this.loadData(null);
                } catch (SQLException ex) {
                    Utils.getBox("Add failed!", Alert.AlertType.INFORMATION).show();
                    Logger.getLogger(FXMLBusController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                Utils.getBox("Please enter full info!!!", Alert.AlertType.INFORMATION).show();
            }
        }
    }
    
    public void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    
    public void deleteBusHandler(ActionEvent event) throws SQLException {
        Bus bus = tableBus.getSelectionModel().getSelectedItem();
        if (bus != null) {
            if (Utils.getBox("Are you sure want to delete bus have id = " + bus.getId() + "?", Alert.AlertType.CONFIRMATION).showAndWait().get() == ButtonType.OK) {
                if (S.deleteBus(bus.getId())) {
                    Utils.getBox("Delete successful!", Alert.AlertType.INFORMATION).show();
                    this.loadData(null);
                }
                else {
                    Utils.getBox("Delete failed!", Alert.AlertType.WARNING).show();
                }
            }
        }
        else {
            Utils.getBox("Select the bus to delete from table bus!", Alert.AlertType.WARNING).show();
        }
    }
}
