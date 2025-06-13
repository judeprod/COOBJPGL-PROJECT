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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginpageController {

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        App.setRoot("firstscene");
    }
    
    @FXML
    TextField nametextfield;

    @FXML
    TextField passwordtextfield;

    @FXML
    Button loginButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static User user;


    public void loginButtonHandler(ActionEvent event) throws IOException {
        System.out.println("\nlogin button is clicked");

        // Get text from text field
        String username = nametextfield.getText();
        String password = passwordtextfield.getText();  
  
        user = new User(username, password);


    File accountsfile = new File("Accounts.txt");

System.out.println("Working directory: " + System.getProperty("user.dir"));
System.out.println("Full path to Accounts.txt: " + accountsfile.getAbsolutePath());
System.out.println("File exists? " + accountsfile.exists());

        if (accountsfile.exists()) {

            Scanner filescanner = new Scanner(accountsfile);
            while (filescanner.hasNextLine()) {

                String data = filescanner.nextLine();
    
                String username_from_file = data.split(",")[0];
                String password_from_file = data.split(",")[1];
                String phonenumber_from_file = data.split(",")[2];


                if (username_from_file.equals(user.getUsername()) && password_from_file.equals(user.getPassword())) {
                    
                    System.out.println("\nLogin successful\n");

                    System.out.println("Accessing account... \nName/ Password/ Phonenumber: ");
                    System.out.println(username_from_file +"/ "+ password_from_file +"/ "+ phonenumber_from_file + "\n");

                
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
                    root = loader.load();
                    // Load stage and scene
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;

                    } else if(password.isEmpty() || username.isEmpty()) {

                    Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Missing Fields");
                            alert.setHeaderText("All fields are required");
                            alert.setContentText("Please enter your name and password");
                            alert.showAndWait();
                            break;

                        } else {

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Account does not exist");
                    alert.setContentText("Please enter your name and password");
                    alert.showAndWait();
                    break; 

                        
            
                }
            }
        }
    }    
}