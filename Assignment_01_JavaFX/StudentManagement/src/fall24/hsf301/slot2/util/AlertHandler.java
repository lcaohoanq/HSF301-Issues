package fall24.hsf301.slot2.util;

import javafx.scene.control.Alert;

public class AlertHandler {


    public static void showAlert(String title, String content) {
        Alert alert = new Alert(title.toLowerCase().contains("error") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	
}
