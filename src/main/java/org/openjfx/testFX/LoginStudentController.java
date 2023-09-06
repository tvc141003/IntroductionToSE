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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import service.StudentServiceImpl;

public class LoginStudentController {

	@FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private PasswordField passWord;

    @FXML
    private TextField userName;

    private static String Id;
    
    public LoginStudentController()
    {
    	
    }
    public String getId()
    {
    	return this.Id;
    }
    @FXML
    public void forgotPassword(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("forgotPasswordStudent.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
    	System.out.print(userName.getText());
    	this.Id = userName.getText();
    	if(StudentServiceImpl.getInstance().login(userName.getText(), passWord.getText()))
    	{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Message: Login successfully.");

            ButtonType buttonTypeOK = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOK);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeOK) {
                	Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("menuHomeStudent.fxml"));
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
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Message: Login unsuccessfully.");

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
    }

}
