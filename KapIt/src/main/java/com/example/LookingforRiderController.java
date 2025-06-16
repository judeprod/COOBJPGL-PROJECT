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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class LookingforRiderController {

    ObservableList<ACInfo> mylist = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label dropoffLabel;

    @FXML
    private Label bookingprice;

    @FXML
    private ImageView maps;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    public void Cancelbookinghandler(ActionEvent event) throws IOException {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("transport.fxml"));
                    root = loader.load();
                    // Load stage and scene
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                    String formattedTime = currentTime.format(formatter);

                    String location = dropoffLabel.getText();
                    String cost = bookingprice.getText();

    File file = new File("ActivityCenter.txt");


    // Removes booked from recent activity
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
           

        }

        System.out.println("Booking removed to ActivityCenter.txt");

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

