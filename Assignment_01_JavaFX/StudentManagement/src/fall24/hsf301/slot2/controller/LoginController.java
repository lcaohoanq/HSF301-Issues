package fall24.hsf301.slot2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
	
	
	private IStudentService StudentService;
	public LoginController() {
		StudentService  = new StudentService("hibernate.cfg.xml", 2);
	}
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
    public void btnLoginOnAction() {
        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        
        try {
            if ("admin".equals(username) && "admin".equals(password)) {
                openStudentManagementWindow();
            } else {
                showAlert("Login Failed", "Invalid username or password.", AlertType.ERROR);
            }
        } catch (IOException e) {
            showAlert("Error", "An error occurred while opening the Student Management window.", AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private void openStudentManagementWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/StudentManagementGUI.fxml"));
        Parent root = loader.load();
        
        StudentManagementController smController = loader.getController();
        smController.setRoleID(1);
        
        Stage currentStage = (Stage) txtPassword.getScene().getWindow();
        currentStage.close();
        
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management");
        primaryStage.show();
    }
	
	@FXML
	public void btnCancelOnAction() {
		Platform.exit();
	}
	
	private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	
}
