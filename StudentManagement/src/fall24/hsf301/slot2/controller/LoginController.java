package fall24.hsf301.slot2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	
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
		Student st = studentService.findById(Long.parseLong(txtUserName.getText()));
		try {
			if(st.getFirstName().equals(txtPassword.getText())) {
				System.out.println(st);
			}
		}catch(Exception e) {
			System.out.println("Login fail" + e.getMessage());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
