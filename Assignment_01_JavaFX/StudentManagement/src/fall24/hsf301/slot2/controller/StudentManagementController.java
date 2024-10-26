package fall24.hsf301.slot2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.service.IStudentService;
import fall24.hsf301.slot02.service.StudentService;
import fall24.hsf301.slot2.util.AlertHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentManagementController implements Initializable {

    private final IStudentService StudentService;
    private final ObservableList<Student> tableModel;

    @FXML private TextField txtStudentId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtMark;
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnCancel;
    @FXML private TableView<Student> tblStudents;
    @FXML private TableColumn<Student, Long> studentId;
    @FXML private TableColumn<Student, String> firstName;
    @FXML private TableColumn<Student, String> lastName;
    @FXML private TableColumn<Student, Integer> mark;
    
    private int roleID;
    public static String hibernateConfig = "hibernate.cfg.xml";
    public static String jpaConfig = "JPAs";

    public StudentManagementController() {
        StudentService = new StudentService(hibernateConfig,2);
        tableModel = FXCollections.observableArrayList(StudentService.getStudents());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        tblStudents.setItems(tableModel);
        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showStudent(newValue);
                //disable the id text field when initialize
                //readonly
                txtStudentId.setEditable(false);
            }
        });
    }

    private void initializeTableColumns() {
        studentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
    }

    @FXML
    public void btnAddOnAction() {
        try {
            Student newStudent = getStudentFromInput();
            StudentService.save(newStudent);
            refreshDataTable();
            AlertHandler.showAlert("Success", "Student successfully added.");
        } catch (Exception e) {
        	AlertHandler.showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void btnUpdateOnAction() {
        try {
            Long id = Long.parseLong(txtStudentId.getText().trim());
            Student existingStudent = StudentService.findById(id);
            if (existingStudent == null) {
                throw new Exception("Student not found with id: " + id);
            }
            updateStudentFromInput(existingStudent);
            StudentService.update(existingStudent);
            refreshDataTable();
            AlertHandler.showAlert("Success", "Student updated successfully.");
        } catch (Exception e) {
        	AlertHandler.showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void btnDeleteOnAction() {
        try {
            Long id = Long.parseLong(txtStudentId.getText().trim());
            if (StudentService.findById(id) == null) {
                throw new Exception("Student not found with id: " + id);
            }
            StudentService.delete(id);
            refreshDataTable();
            AlertHandler.showAlert("Success", "Student deleted successfully.");
        } catch (Exception e) {
        	AlertHandler.showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void btnCancelOnAction() {
        Platform.exit();
    }

    private Student getStudentFromInput() throws Exception {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String markStr = txtMark.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || markStr.isEmpty()) {
            throw new Exception("All fields are required.");
        }

        int mark = Integer.parseInt(markStr);
        if (mark < 0 || mark > 10) {
            throw new Exception("Mark must be between 0 and 10.");
        }

        return new Student(firstName, lastName, mark);
    }

    private void updateStudentFromInput(Student Student) throws Exception {
        Student updatedInfo = getStudentFromInput();
        Student.setFirstName(updatedInfo.getFirstName());
        Student.setLastName(updatedInfo.getLastName());
        Student.setMark(updatedInfo.getMark());
    }

    private void showStudent(Student Student) {
        txtStudentId.setText(String.valueOf(Student.getId()));
        txtFirstName.setText(Student.getFirstName());
        txtLastName.setText(Student.getLastName());
        txtMark.setText(String.valueOf(Student.getMark()));
    }

    private void refreshDataTable() {
        tableModel.setAll(StudentService.getStudents());
        clearInputFields();
    }

    private void clearInputFields() {
        txtStudentId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtMark.clear();
    }

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
		
		switch (this.roleID) {
		case 1: {
			this.btnAdd.setDisable(false);
			this.btnDelete.setDisable(false);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.roleID);
		}
		
	}
    
    
    
    
}