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
	
	private IStudentService studentService;
	public LoginController() {
		studentService  = new StudentService("JPAs");
	}
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	public void btnLoginOnAction(){
		
		String username = txtUserName.getText();
		String password = txtPassword.getText();
		
		System.out.println("Username ne: " + username);
		System.out.println("Password ne: " + password);
		
//		Student st = studentService.findById(1L);

		
		try {
			if(username.equals("admin") && password.equals("admin")) {
				Stage win = (Stage) txtPassword.getScene().getWindow();
				win.close();
				AnchorPane root = FXMLLoader.load(getClass().getResource("../application/StudentManagementGUI.fxml"));
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root);
	            // Set the scene
	            primaryStage.setScene(scene);
	            // Show the stage
	            primaryStage.show();
			}else {
				Alert a = new Alert(AlertType.INFORMATION);
				a.show();
			}
			
//			if(!(st.getFirstName().equals(username) && st.getLastName().equals(password))) {
//				throw new Exception("Login fail");
//			}
//			System.out.println("Login success");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void btnCancelOnAction() {
		Platform.exit();
	}
	
	
}
