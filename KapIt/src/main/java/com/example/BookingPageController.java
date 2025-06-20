package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    

    public static String latestBookedLocation;

        ObservableList<ACInfo> mylist = FXCollections.observableArrayList();

    @FXML
    private Label dropoffLabel; // or TextField, whatever you're using

    @FXML
    private Label bookingprice;

    @FXML
    private ImageView maps;

     private Location currentLocation;

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
    //Booking button pressed
    @FXML
    private void finalbookinghandler(ActionEvent event) {


    LocalTime currentTime = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    String formattedTime = currentTime.format(formatter);

        String location = dropoffLabel.getText();
        String cost = bookingprice.getText();
        String mapFilePath = currentLocation.getMaps(); 
        latestBookedLocation = location;

        

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ActivityCenter.txt", true))) {
            writer.write(formattedTime + "," + location + "," + cost);
            writer.newLine();

            // Add to observable list (optional)
            mylist.add(new ACInfo(formattedTime, location, cost));

            System.out.println("Booking saved to ActivityCenter.txt");
 
        // find rider
            FXMLLoader loader = new FXMLLoader(getClass().getResource("findingdriver.fxml"));
            Parent root = loader.load();

            FindingdriverController controller = loader.getController();
            controller.setBookingInfo(location, cost, mapFilePath);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
   }




 public void setLocation(Location location) {
        currentLocation = location;  // ✅ STORE the Location object for later

        dropoffLabel.setText(location.getTitle()); 
        bookingprice.setText("₱" + location.getBprice());

        if (location.getMaps() != null && !location.getMaps().isEmpty()) {
            File imageFile = new File(location.getMaps());
            if (imageFile.exists()) {
                maps.setImage(new Image(imageFile.toURI().toString()));
            } else {
                maps.setImage(null);
            }
        } else {
            maps.setImage(null);
        }
    }
}
