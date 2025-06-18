package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KapItController {

    @FXML // log-in button
    private void switchToLoginpage(ActionEvent event) throws IOException {
        //App.setRoot("Loginpage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loginpage.fxml"));
        Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                    stage.show();
        }

    @FXML // registration button
    private void switchToPhoneregister(ActionEvent event) throws IOException {
        //App.setRoot("phoneregister");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("phoneregister.fxml"));
        Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                    stage.show();

    }
    
}
