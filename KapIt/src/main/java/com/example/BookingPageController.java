package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookingPageController {

    @FXML
    private Label dropoffLabel; // or TextField, whatever you're using

    public void setDropoffLocation(String location) {
        dropoffLabel.setText(location);
    }
}