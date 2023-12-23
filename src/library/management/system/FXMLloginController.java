package library.management.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLloginController {

    @FXML
    private PasswordField Password;

    @FXML
    private TextField studeNumer;

    @FXML
    private Label Label;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Button login;

    @FXML
    private void btnlognin(ActionEvent event) {
        String studentSql = "SELECT * FROM student WHERE studentNumber = ? AND password = ?";

        try {
            if (studeNumer.getText().isEmpty() || Password.getText().isEmpty()) {
                showAlert("Error", "Please enter both student number and password.");
                return;
            }

            connect = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase", "root", "");

            prepare = connect.prepareStatement(studentSql);
            prepare.setString(1, studeNumer.getText());
            prepare.setString(2, Password.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                openStudentDashboard();
            } else {
                showAlert("Error", "Invalid credentials. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "SQL Error: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void closeResources() {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert("Error", "SQL Error during resource closing: " + ex.getMessage());
        }
    }

    private void openStudentDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) studeNumer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "IOException: " + e.getMessage());
        }
    }
}
