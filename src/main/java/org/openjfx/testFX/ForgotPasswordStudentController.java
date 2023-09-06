package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import service.ManagerServiceImpl;
import service.StudentServiceImpl;

public class ForgotPasswordStudentController {

    @FXML
    private TextField email;
    @FXML
    private TextField Username;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void sendMail(ActionEvent event) throws IOException{
    	if(StudentServiceImpl.getInstance().forgotPassword(Username.getText(), email.getText()))
    	{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Message: Send Email successfully.");

            ButtonType buttonTypeOK = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOK);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeOK) {
                	Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
						Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	                	scene = new Scene(root);
	                	stage.setScene(scene);
	                	stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            });
    	}
    	else
    	{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Message: Send Email Error.");

            ButtonType buttonTypeOK = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOK);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeOK) {
                	try {
						root = FXMLLoader.load(getClass().getResource("forgotPasswordStudent.fxml"));
						Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	                	scene = new Scene(root);
	                	stage.setScene(scene);
	                	stage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
                }
            });
    	}
    }

}
