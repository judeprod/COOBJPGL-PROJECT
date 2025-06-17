package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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

public class FindingdriverController {


private Stage stage;
private Scene scene;
private Parent root;

@FXML
private Label dropoffLabel;

@FXML
private Label bookingprice;

@FXML
private Button homebtn;


// Timer
   private PauseTransition timer;
    private Timeline countdownTimeline;
    private int secondsRemaining = 5;

    @FXML
    public void initialize() {
        startCountdownVisualization();
        startTimer();
    }

    private void startTimer() {
        timer = new PauseTransition(Duration.seconds(5));
        timer.setOnFinished(event -> {
            stopCountdownVisualization();  // stop visualization when timer finishes
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("rideishere.fxml"));
                Parent root = loader.load();

                // Get controller
                RideishereController rideishereController = loader.getController();
                // Pass location from BookingPageController
                rideishereController.setDropoffLocation(BookingPageController.latestBookedLocation);


                Stage stage = (Stage) homebtn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timer.play();
    }

    private void startCountdownVisualization() {
        System.out.println("Waiting for driver...");
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            System.out.println("Seconds remaining: " + secondsRemaining);
            secondsRemaining--;
            if (secondsRemaining < 0) {
                countdownTimeline.stop();
            }
        }));
        countdownTimeline.setCycleCount(secondsRemaining + 1);
        countdownTimeline.play();
    }

    private void stopCountdownVisualization() {
        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }
    }

    // Cancel Button
    
public void homedriverHandler(ActionEvent event) throws IOException {
    System.out.println("Cancel button Clicked");

         if (timer != null) {
            timer.stop();
        }
        if (countdownTimeline != null) {
            countdownTimeline.stop();
        }
        secondsRemaining = 5;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("transport.fxml"));
    root = loader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    // File path
    File file = new File("ActivityCenter.txt");

    try {
        // Step 1: Read all lines from the file
        List<String> lines = new ArrayList<>();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        }

        // Step 2: Remove the last line (most recent booking)
        if (!lines.isEmpty()) {
            lines.remove(lines.size() - 1); // Remove the last line
        }

        // Step 3: Write the remaining lines back to the file (excluding the last line)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }


        }

        System.out.println("Booking removed from ActivityCenter.txt");

            
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
