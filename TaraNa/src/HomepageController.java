package com.example;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class HomepageController implements Initializable {
    
    ObservableList<User> mylist = FXCollections.observableArrayList();

    @FXML
    Label nameLabel;

    @FXML
    private Button MotoTaxiButton;

    @FXML
    private Button ActivityCenterButton;

    @FXML
    private Button AccountButton;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    

      initializeCol();
      loadData();

        String user = LoginController.user.getUsername();

        nameLabel.setText("Hello, " + user + "!");
        
    }

    private void initializeCol() {

        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
        accountcreated_col.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("accountstatus"));

    }

    @FXML
    private boolean MotoTaxiButtonclick(ActionEvent event) {

    }

    @FXML
    private boolean ActivityCenterButtonclick(ActionEvent event) {

    }

    @FXML
    private boolean AccountButtonclick(ActionEvent event) {

    }

}
