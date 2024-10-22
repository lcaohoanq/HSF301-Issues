package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pe.hsf301.fall24.pojo.Account;
import pe.hsf301.fall24.repository.account.AccountRepo;
import pe.hsf301.fall24.repository.account.IAccountRepository;

public class LoginController {

	private IAccountRepository accountRepository;

	public LoginController() {
		accountRepository = new AccountRepo("hibernate.cfg.xml");
	}

	@FXML
	private TextField txtUserName;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtPassword;

	private Account account;

	@FXML
	public void btnLoginOnAction() {
		String username = txtUserName.getText().trim();
		String password = txtPassword.getText().trim();

		System.out.println("Username: " + username);
		System.out.println("Password: " + password);

		try {

			account = accountRepository.login(username, password);

			if (account == null) {
				throw new IOException("You have no permission to access this function!");
			}

			openMovieManagementWindow(account);
		} catch (IOException e) {
			showAlert("Login Failed", e.getMessage(), AlertType.ERROR);
			System.out.println(e.getMessage());
		}
	}

	private void openMovieManagementWindow(Account account) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/MovieManagementGUI.fxml"));
        Parent root = loader.load();
        
        MoiveManagementController smController = loader.getController();
        
        //1: admin, 2: staff, 3: manager, 4: customer
        switch (account.getRoleId()) {
        case 1: {
            smController.setRoleID(1);
            break;
        }
        case 2: {
            smController.setRoleID(2);
            break;
        }
        case 3: {
            smController.setRoleID(3);
            break;
        }
        case 4: {
            smController.setRoleID(4);
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + account.getRoleId());
    	}

        
        Stage currentStage = (Stage) txtPassword.getScene().getWindow();
        currentStage.close();
        
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Management");
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
