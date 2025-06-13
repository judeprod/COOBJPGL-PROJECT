package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class phoneregisterController {


    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        App.setRoot("firstscene");
    }
    

    @FXML
    private Label phareacode;

    @FXML
    private ImageView phflag;

    @FXML
    private TextField Phonenumbertextfield;

    @FXML
    private TextField Newnametextfield;

    @FXML
    private TextField Newpasswordtextfield;

    @FXML
    private Button signupButton;

    public static CreateUser createuser;

    @FXML
    private boolean signupButtonHandler(ActionEvent event) {
        System.out.println("\nSignup button is clicked");

        String newusername = Newnametextfield.getText().trim();

        String newpassword = Newpasswordtextfield.getText().trim();

        String phonenumber = Phonenumbertextfield.getText().trim();

        // Create user object
        CreateUser createuser = new CreateUser(newusername, newpassword, phonenumber);

        if(newpassword.isEmpty() || newusername.isEmpty() || phonenumber.isEmpty()) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Missing Fields");
        alert.setHeaderText("All fields are required");
        alert.setContentText("Please enter your name, password, and phone number.");
        alert.showAndWait();
        return false; 

        }

        try {

        System.out.println("Registering User...\nUser Registered!\nAccessing account...\nName/ Password/ Phonenumber: ");
        System.out.println(newusername +"/ "+ newpassword +"/ "+ phonenumber);

            BufferedWriter myWriter = new BufferedWriter(new FileWriter("Accounts.txt", true));
      
            // .write() methods adds content to the file
            myWriter.newLine(); // adds a new line
            myWriter.write(createuser.getNewUsername() + "," + createuser.getNewPassword() + "," + createuser.getPhonenumber());

            // Close FileWriter
            myWriter.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Account Created");
            alert.setContentText("You have created a new account!");
            alert.showAndWait();
            
            App.setRoot("firstscene");
        

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return true;
    }   

    
}

