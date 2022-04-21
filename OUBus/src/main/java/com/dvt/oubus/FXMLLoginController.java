/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.User;
import com.dvt.sevices.UserSevices;
import com.dvt.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLLoginController implements Initializable {
    private static final UserSevices S = new UserSevices();
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private Stage stage;
    @FXML private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    public void loginHandler(ActionEvent event) throws SQLException, IOException {
        FXMLLoader managerLoader = new FXMLLoader(getClass().getResource("FXMLManager.fxml"));
        FXMLLoader staffLoader = new FXMLLoader(getClass().getResource("FXMLStaff.fxml"));
        String username = txtUsername.getText().strip();
        String password = txtPassword.getText().strip();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (!username.equals("") && !password.equals("")) {
            User user = S.getUserByUsername(username);
            if (S.checkAccount(username, password)) {
                if (user.getUserRole()) {
                    scene = new Scene(staffLoader.load());
                    FXMLStaffController staffController = staffLoader.getController();
                    staffController.setUser(user);
                    staffController.load();
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.setScene(scene);
//                    stage.show();
                }
                else {
                    scene = new Scene(managerLoader.load());
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else {
                Utils.getBox("User not exist!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("Username and password connot be empty!", Alert.AlertType.ERROR).show();
        }
    }
    
    public void cancelHandler(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
