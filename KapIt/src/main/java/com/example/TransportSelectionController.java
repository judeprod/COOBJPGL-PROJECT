package com.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TransportSelectionController{
    @FXML
    private TextField dropofftf;
    @FXML 
    private TableView<Location> placestable;

    @FXML 
    private TableColumn<Location, String> recentplacestable;

    @FXML
    private void backbuttonpdHandler(ActionEvent event) throws IOException { 
    FXMLLoader loader = new FXMLLoader(getClass().getResource("transport.fxml"));
        Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                    stage.show();
}

    private ObservableList<Location> allLocations = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        // Load locations from file and shows them
        loadLocationsFromFile();
        setupTable();
        setupSearch();
        setupRowClick();
    }

    private void loadLocationsFromFile() {
    File file = new File("locations.txt");

    if (file.exists()) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 2) { // accept 2 or 3 parts
                    String loc = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    String imageName = (parts.length == 3) ? parts[2].trim() : ""; // handle missing image

                    allLocations.add(new Location(loc, price, imageName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    private void setupRowClick() {
        placestable.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) { // double-click (you can use 1 for single click)
            Location selected = placestable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                goToBookingPage(selected);
            }
        }
    });
}
private void goToBookingPage(Location selectedLocation) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingPage.fxml"));
        Parent root = loader.load();

        // Get the controller and pass data
        BookingPageController controller = loader.getController();
        controller.setLocation(selectedLocation);

        // Set new scene
        Stage stage = (Stage) placestable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    private void setupTable() {
        recentplacestable.setCellValueFactory(new PropertyValueFactory<>("loc"));
        placestable.setItems(allLocations);
    }

    private void setupSearch() {
        dropofftf.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    private void filterTable(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            placestable.setItems(allLocations);
        } else {
            List<Location> filtered = allLocations.stream()
                    .filter(loc -> loc.getLoc().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
            placestable.setItems(FXCollections.observableArrayList(filtered));
        }
    }
}


