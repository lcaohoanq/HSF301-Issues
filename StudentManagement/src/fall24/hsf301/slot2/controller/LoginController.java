package fall24.hsf301.slot2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
	public void btnLoginOnAction() {
		
		String username = txtUserName.getText();
		String password = txtPassword.getText();
		
		System.out.println("Username ne: " + username);
		System.out.println("Password ne: " + password);
		
		Student st = studentService.findById(1L);
		try {
			if(!(st.getFirstName().equals(username) && st.getLastName().equals(password))) {
				throw new Exception("Login fail");
			}
			System.out.println("Login success");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void btnCancelOnAction() {
		if(!txtUserName.getText().isEmpty()) {
			txtUserName.setText("");
		}
		if(!txtPassword.getText().isEmpty()) {
			txtPassword.setText("");
		}
	}
	
	
}
