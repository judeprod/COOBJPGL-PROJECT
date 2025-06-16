package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class firstsceneController {

    @FXML
    private void switchToLoginpage() throws IOException {
        App.setRoot("Loginpage");
    }

    @FXML
    private void switchToPhoneregister() throws IOException {
        App.setRoot("phoneregister");
    }
    
}
