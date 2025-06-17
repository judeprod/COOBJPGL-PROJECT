package com.example;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RideishereController {

    private int secondsRemaining = 7; 
    private Timeline countdownTimeline;
    @FXML
    private Label locationLabel;

    @FXML
    Button homebutton;


    public void setDropoffLocation(String location) {
        locationLabel.setText(location);
    }

    @FXML
    public void initialize() {
    startTerminalCountdownAndSwitch();
    javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(7));
    delay.setOnFinished(event -> switchToBookingCompleted());
    delay.play();
}

    private void startTerminalCountdownAndSwitch() {
        System.out.println("Trip Ending in...");
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            System.out.println("Seconds remaining: " + secondsRemaining);
            secondsRemaining--;

            if (secondsRemaining < 0) {
                countdownTimeline.stop();
                switchToBookingCompleted();
            }
        }));
        countdownTimeline.setCycleCount(secondsRemaining + 1);
        countdownTimeline.play();
    }

    private void switchToBookingCompleted() {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingcompleted.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) locationLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Booking Completed");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


