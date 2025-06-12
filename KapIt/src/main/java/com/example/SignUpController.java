package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SignUpController {

    @FXML
    private void signupbtnHandler(ActionEvent event) throws IOException {
        App.setRoot("register"); 
    }

    @FXML
    private void loginbtnHandler(ActionEvent event) throws IOException {
        App.setRoot("LoginMoveIt"); 
    }
     @FXML
    private void nextbtnHandler(ActionEvent event) throws IOException {
       App.setRoot("LoginMoveit"); // Replace "nextScreen" with your actual next FXML file name (without .fxml)
    }
     @FXML
    private void bookingbackbtnHandler(ActionEvent event) throws IOException {
       App.setRoot("signup"); // Replace "nextScreen" with your actual next FXML file name (without .fxml)
    }
    
}
