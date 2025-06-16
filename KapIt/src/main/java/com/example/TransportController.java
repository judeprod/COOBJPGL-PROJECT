package com.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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


   @FXML
    private void shomebtnHandler(ActionEvent event) {
        OTrasportSelection("SM San Lazaro Felix Huertas Corner 1003 Lacson Ave Santa Cruz City Of Manila", event);
    }

    @FXML
    private void worktbtnHandler(ActionEvent event) {
        OTrasportSelection("NU ANNEX II Concepcion Aguila St San Miguel City Of Manila 1008 Metro Manila", event);
    }

    @FXML
    private void newtbtnHandler(ActionEvent event) {
        OTrasportSelection("Dangwa Beautiful Blossoms Ph   1633F Dimasalang St Santa Cruz Sta Cruz 1008 Metro Manila", event);
    }

     private void OTrasportSelection(String presetLocation, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transportselection.fxml"));
            Parent root = loader.load();

            // Immediately look for the location in the file and go to booking
            if (presetLocation != null) {
                Location selectedLocation = findLocationFromFile(presetLocation);
                if (selectedLocation != null) {
                    FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("BookingPage.fxml"));
                    Parent bookingRoot = bookingLoader.load();

                    BookingPageController controller = bookingLoader.getController();
                    controller.setLocation(selectedLocation);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(bookingRoot));
                    stage.show();
                    return; // skip loading transportselection
                } else {
                    System.out.println("Location not found: " + presetLocation);
                }
            }

            // If no preset location found or null, go to transport selection screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This reads locations.txt and finds a matching Location
    private Location findLocationFromFile(String target) {
        File file = new File("locations.txt");

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length >= 2) {
                        String loc = parts[0].trim();
                        int price = Integer.parseInt(parts[1].trim());
                        String image = (parts.length == 3) ? parts[2].trim() : "";

                        if (loc.equalsIgnoreCase(target)) {
                            return new Location(loc, price, image);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}