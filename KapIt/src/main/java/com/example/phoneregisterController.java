package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class phoneregisterController {


    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstscene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
                stage.show();
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

    private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
//Only Numbers typable in phonenumber text field
private void restrictPhoneNumberInput() {
      Phonenumbertextfield.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
      Phonenumbertextfield.setText(newValue.replaceAll("[^\\d]", ""));
      }
//limit phonenumber text field
      if (newValue.length() > 11) {
      newValue = newValue.substring(0, 11);
      }
      Phonenumbertextfield.setText(newValue);
    });
    
    //Also limiter for Name up to 30 characters
      Newnametextfield.textProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue.length() > 30) {
      Newnametextfield.setText(oldValue);
     }
    });
     //Also limiter for Password up to 30 characters
      Newpasswordtextfield.textProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue.length() > 20) {
      Newpasswordtextfield.setText(oldValue);
     }
    });
}

//Only Numbers typable in phonenumber text field (initialize)
@FXML
public void initialize() {
    restrictPhoneNumberInput();
}

//format phonenumber "1234-567-890" pattern
private String formatPhoneNumber(String digits) {
        if (digits.length() == 11) {
            return digits.substring(0, 4) + "-" + digits.substring(4, 7) + "-" + digits.substring(7);
        } else if (digits.length() == 10) {
            return digits.substring(0, 3) + "-" + digits.substring(3, 6) + "-" + digits.substring(6);
        } else {
            return digits;
        }
    }


    //Sign up button
    @FXML
    private boolean signupButtonHandler(ActionEvent event) {
        System.out.println("\nSignup button is clicked");
    
        String newusername = Newnametextfield.getText().trim();

        String newpassword = Newpasswordtextfield.getText().trim();

        String phonenumber = Phonenumbertextfield.getText().trim();

        //Creating phonenumber pattern object
        String formattedPhone = formatPhoneNumber(phonenumber);

        // Creating user object
        CreateUser createuser = new CreateUser(newusername, newpassword, formattedPhone);

        if(newpassword.isEmpty() || newusername.isEmpty() || phonenumber.isEmpty()) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Missing Fields");
        alert.setHeaderText("All fields are required");
        alert.setContentText("Please enter your name, password, and phone number.");
        alert.showAndWait();
        return false; 

        }

        
       
    // Alert for missing inputs in text fields
        if (!newusername.matches("[a-zA-Z ]{3,30}")) {
        showAlert("Invalid Name", "Name should only contain letters, numbers, and spaces", "Character length 3 to 30");
        return false;
    }

        if (!phonenumber.matches("\\d{10}")) {
        showAlert("Invalid Phone Number", "Phone number should be 10 or 11 digits", "Numbers only.");
        return false;
    }

        if (!newpassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
        showAlert("Weak Password", "Password must be at least 8 characters long",
        "Include 1 uppercase, 1 lowercase, and 1 number.");
        return false;
    }

        
    // Database writer
        try {

        System.out.println("Registering User...\nUser Registered!\nAccessing account...\nName/ Password/ Phonenumber: ");
        System.out.println(newusername +"/ "+ newpassword +"/ "+ formattedPhone);

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

