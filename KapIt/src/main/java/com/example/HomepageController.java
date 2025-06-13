package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HomepageController {


    @FXML
    private Button ActivityCenterButton;

@FXML
private void Activitycenterhandle(ActionEvent event) throws IOException {
    System.out.println("Activity Center Button Clicked");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Activitycenter.fxml"));
Parent root = loader.load();
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
stage.setScene(new Scene(root));
stage.show();
}

    
}
