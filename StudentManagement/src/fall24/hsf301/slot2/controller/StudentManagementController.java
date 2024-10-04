package fall24.hsf301.slot2.controller;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class StudentManagementController {

	private IStudentService studentService;
	public StudentManagementController() {
		studentService  = new StudentService("JPAs");
	}
	
	@FXML
	private TextField txtStudentId;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtMark;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	
	@FXML
	public void btnAddOnAction() {
	    String firstName = txtFirstName.getText().trim();
	    String lastName = txtLastName.getText().trim();
	    String mark = txtMark.getText().trim();

	    System.out.println(String.format("Data ne: %s, %s, %s" , firstName, lastName, mark));
	    
	    try {
	        if (firstName.isEmpty() || lastName.isEmpty() || mark.isEmpty()) {
	            throw new Exception("All fields are required.");
	        }
	        
	        // Try to parse the mark to ensure it's a valid number
	        int markValue = Integer.parseInt(mark);
	        
	        if(markValue < 0 || markValue > 10) throw new Exception("Mark from 1 to 10");

	        // Save the student details
	        studentService.save(new Student(firstName, lastName, markValue));

	        // Show success alert
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Success");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Student successfully added.");
	        successAlert.showAndWait();

	    } catch (NumberFormatException e) {
	        // Handle invalid number input for mark
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Invalid Input");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText("Please enter a valid number for the mark.");
	        errorAlert.showAndWait();

	    } catch (Exception e) {
	        // Handle any other exceptions (e.g., missing fields)
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Error");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText(e.getMessage());
	        errorAlert.showAndWait();
	    }
	}

	@FXML
	public void btnUpdateOnAction() {
		String id = txtStudentId.getText().trim();
	    String firstName = txtFirstName.getText().trim();
	    String lastName = txtLastName.getText().trim();
	    String mark = txtMark.getText().trim();
	    
	    try {
	    	
	    	Student existingStudent = studentService.findById(Long.parseLong(id));    	

	    	if(existingStudent == null) {
	    		throw new Exception(String.format("Not found student with id : %s", id));
	    	}
	    	
	    	int markValue = Integer.parseInt(mark);
	    	
	    	if(markValue < 0 || markValue > 10) throw new Exception("Mark from 1 to 10");
	    	
	    	existingStudent.setFirstName(firstName);
	    	existingStudent.setLastName(lastName);
	    	existingStudent.setMark(markValue);
	    	
	    	studentService.update(existingStudent);
	    	
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Success");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Update successfully");
	        successAlert.showAndWait();
	    	
	    }catch (Exception e) {
	        // Handle any other exceptions (e.g., missing fields)
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Error");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText(e.getMessage());
	        errorAlert.showAndWait();
		}
	    
	}
	
	@FXML
	public void btnDeleteOnAction() {
		String id = txtStudentId.getText().trim();
	    
	    try {

	    	if(studentService.findById(Long.parseLong(id)) == null) {
	    		throw new Error(String.format("Not found student with id : %s", id));
	    	}
	    	
	    	studentService.delete(Long.parseLong(id));
	    	
	        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
	        successAlert.setTitle("Success");
	        successAlert.setHeaderText(null);
	        successAlert.setContentText("Delete successfully");
	        successAlert.showAndWait();
	    	
	    }catch (Exception e) {
	        // Handle any other exceptions (e.g., missing fields)
	        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        errorAlert.setTitle("Error");
	        errorAlert.setHeaderText(null);
	        errorAlert.setContentText(e.getMessage());
	        errorAlert.showAndWait();
		}
	}
	
	@FXML
	public void btnCancleOnAction() {
		Platform.exit();
	}
	
	
}
