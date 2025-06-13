package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TransportController {

    @FXML
private void wheretobuttonHandler(ActionEvent event) throws IOException { 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("transportselection.fxml"));
        Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                    stage.show();
}

 @FXML
private void backtohomebtnHandler(ActionEvent event) throws IOException { 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                    stage.show();

}
  // 🏠 Placeholder for the "Home" saved place button
    @FXML
    private void shomebtnHandler(ActionEvent event) {
        System.out.println("Home button clicked - placeholder logic.");
    }

    // 🎒 Placeholder for the "School" saved place button
    @FXML
    private void worktbtnHandler(ActionEvent event) {
        System.out.println("School button clicked - placeholder logic.");
    }

    // 🔖 Placeholder for the "New" saved place button
    @FXML
    private void newtbtnHandler(ActionEvent event) {
        System.out.println("New button clicked - placeholder logic.");
    }
}

