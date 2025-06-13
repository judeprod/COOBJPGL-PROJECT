package com.example;

import java.io.File;
import java.io.IOException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;




public class ActivitycenterController implements Initializable {

      ObservableList<ACInfo> mylist = FXCollections.observableArrayList();

    @FXML
    private Button HomeButton;

    @FXML
    private TableColumn<ACInfo, String> TimeTable;

    @FXML
    private TableColumn<ACInfo, String> LocationTable;

    @FXML
    private TableColumn<ACInfo, String> CostTable;

    @FXML
    private TableView<ACInfo> ViewTable;

@Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeCol();
        loadData();

        // Time of booking
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        System.out.println("\n" + formattedTime + "\n");
    }

    private void initializeCol() {
        TimeTable.setCellValueFactory(new PropertyValueFactory<>("Time"));
        LocationTable.setCellValueFactory(new PropertyValueFactory<>("Location"));
        CostTable.setCellValueFactory(new PropertyValueFactory<>("Cost"));
    }

    private void loadData() {
        mylist.clear();

        try {
            File myFile = new File("ActivityCenter.txt");

            if (myFile.exists()) {
                Scanner filescanner = new Scanner(myFile);

                while (filescanner.hasNextLine()) {
                    String data = filescanner.nextLine();

                    String[] parts = data.split(",");
                    if (parts.length >= 3) {
                        String Time = parts[0];
                        String Location = parts[1];
                        String Cost = parts[2];

                        mylist.add(new ACInfo(Time, Location, Cost));
                    }
                }

                ViewTable.setItems(mylist);
                filescanner.close();
            } else {
                System.out.println(myFile.getName() + " does not exist!");
            }
        } catch (Exception e) {
            System.out.println("There is an error: " + e.getMessage());
        }
    }

    @FXML
    public void gohome(ActionEvent event) throws IOException {
        System.out.println("Home Button Clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
