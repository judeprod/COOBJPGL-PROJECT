package com.example;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BookingPageController {

    @FXML
    private Label dropoffLabel; // or TextField, whatever you're using

    @FXML
    private Label bookingprice;

    @FXML
    private ImageView maps;

    @FXML
    private void bookingbackbtnHandler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransportSelection.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
         stage.show();
        } catch (IOException e) {
            e.printStackTrace();
    }
}

    private String dropoffLocation;

    public void setDropoffLocation(String location) {
        dropoffLabel.setText(location);
    }
    public void setLocation(Location location) {
        dropoffLabel.setText(location.getTitle()); 
        bookingprice.setText("₱" + location.getBprice());

        // Load image if available
        if (location.getMaps() != null && !location.getMaps().isEmpty()) {
            File imageFile = new File(location.getMaps());
            if (imageFile.exists()) {
                maps.setImage(new Image(imageFile.toURI().toString()));
            } else {
                maps.setImage(null); // Or set a placeholder
            }
        } else {
            maps.setImage(null); // No image available
        }
    }
}
