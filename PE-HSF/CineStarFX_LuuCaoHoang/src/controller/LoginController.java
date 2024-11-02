package controller;




import java.io.IOException;

import com.se181513.pe.pojo.Account;
import com.se181513.pe.repository.AccountRepo;
import com.se181513.pe.repository.IAccountRepo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML Button btnLogin;
	@FXML TextField txtPassword;
	@FXML TextField txtEmail;
	
	private IAccountRepo accountRepo;
	public LoginController() {
		accountRepo = new AccountRepo("hibernate.cfg.xml");
	}
	
	@FXML
	public void btnLoginAction () throws IOException {
		String email = txtEmail.getText();
		String pass = txtPassword.getText();
		Account account = accountRepo.findByUserName(email);
		if(account !=null && account.getPassword().equals(pass) ) {
			Stage win = (Stage) txtEmail.getScene().getWindow();
			win.close();
			if(account.getRoleId()== 3 || account.getRoleId()==1) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../com/se181513/javafx/MoviesManagement.fxml"));
			Parent root = loader.load();
			
			MovieManagementController mmController = loader.getController();
			mmController.setAccount(account); //role authentication
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../com/se181513/javafx/movie_management.css").toExternalForm());
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Login unsuccessfully");
			alert.setContentText("You have no permission to access this function!");
			alert.showAndWait();
		}
	}
	
}
