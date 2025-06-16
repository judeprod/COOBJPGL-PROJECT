package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    
public void homedriverHandler(ActionEvent event) throws IOException {
    System.out.println("Cancel button Clicked");

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
            // Now, append the new booking
            writer.write("");
            writer.newLine();

        }

        System.out.println("Booking removed from ActivityCenter.txt");

            
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
