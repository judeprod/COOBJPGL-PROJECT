package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RideishereController {

    @FXML
    private Label locationLabel;

    public void setDropoffLocation(String location) {
        locationLabel.setText(location);
    }
}
