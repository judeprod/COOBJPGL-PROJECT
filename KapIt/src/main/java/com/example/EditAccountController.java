package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAccountController {

    @FXML
    private TextField Newnametextfield;

    @FXML
    private TextField Phonenumbertextfield;

    @FXML
    private TextField Newpasswordtextfield;

    @FXML
    private Button savebtn;

    @FXML
    private Button DeleteAcc;

    @FXML
    private Label phareacode;

    @FXML
    private void switchtofirstscene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstscene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    private void initialize() {
        loadAccountInfo();
        restrictPhoneNumberInput();
    }

    private void loadAccountInfo() {
        User user = LoginpageController.user;
        if (user == null) return;

        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(user.getUsername())) {
                    Newnametextfield.setText(parts[0]);
                    Newpasswordtextfield.setText(parts[1]);
                    Phonenumbertextfield.setText(parts[2]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account info: " + e.getMessage());
        }
    }
    private void restrictPhoneNumberInput() {
        // Only digits in phone number
        Phonenumbertextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Phonenumbertextfield.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 10) {
                Phonenumbertextfield.setText(newValue.substring(0, 11));
            }
        });

        // Limit name to 30 characters
        Newnametextfield.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                Newnametextfield.setText(oldValue);
            }
        });

        // Limit password to 20 characters
        Newpasswordtextfield.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                Newpasswordtextfield.setText(oldValue);
            }
        });
    }

    @FXML
    private void LogoutHandler(ActionEvent event) {
    System.out.println("Logging Out...");

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Log Out");
    alert.setHeaderText("Are you sure?");
    alert.setContentText("You will be returned to the login screen.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            switchtofirstscene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@FXML
private void backbtnaccHandler(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Home");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void DeleteAccHandler(ActionEvent event) {
        User user = LoginpageController.user;
        if (user == null) return;

        String targetUsername = user.getUsername();
        String filename = "Accounts.txt";
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (!parts[0].equalsIgnoreCase(targetUsername)) {
                        updatedLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Account Deleted");
        alert.setContentText("User '" + targetUsername + "' has been deleted.");
        alert.showAndWait();

          try {
            switchtofirstscene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     @FXML
    private void savebtnHandler(ActionEvent event) {
        User user = LoginpageController.user;
        if (user == null) return;

        String newUsername = Newnametextfield.getText().trim();
        String newPassword = Newpasswordtextfield.getText().trim();
        String newPhone = Phonenumbertextfield.getText().trim();

            if (!newUsername.matches("[a-zA-Z ]{3,30}")) {
            showAlert("Invalid Name", "Name should only contain letters and spaces", "Character length must be 3 to 30.");
            return;
    }

            if (!newPhone.matches("\\d{10,11}")) {
            showAlert("Invalid Phone Number", "Phone number must be 10 or 11 digits.", "Numbers only.");
            return;
    }

            if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            showAlert("Weak Password", "Password must be at least 8 characters long", "Include 1 uppercase, 1 lowercase, and 1 number.");
            return;
    }

        if (newUsername.isEmpty() || newPassword.isEmpty() || newPhone.isEmpty()) {
            showAlert("Missing Fields", "Please fill in all the fields.", "");
            return;
        }

        String targetUsername = user.getUsername();
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(targetUsername)) {
                    updatedLines.add(newUsername + "," + newPassword + "," + newPhone);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt"))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setPhone(newPhone);

        showAlert("Success", "Account updated successfully.", "");
    }

    private void showAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
}
