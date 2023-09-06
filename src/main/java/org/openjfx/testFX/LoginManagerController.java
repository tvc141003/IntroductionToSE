package org.openjfx.testFX;


import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import service.ManagerServiceImpl;

public class LoginManagerController {

    @FXML
    private ImageView ig;
    private Button mybtn;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button loginButton;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private static String Id;
    public String getId()
    {
    	return this.Id;
    }
    private ManagerServiceImpl manager;
    
    public LoginManagerController() {
    	
    }
    public String getUserName()
    {
    	return this.Id;
    }
    @FXML
    public void login(ActionEvent event) throws IOException {
    	this.Id = userName.getText();
    	manager = ManagerServiceImpl.getInstance();
    	String username = userName.getText();
    	String password = passWord.getText();
    	System.out.print("nguyen");
    	if(manager.login(username, password))
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
						root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
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
            alert.setContentText("Message: Login unsuccessful.");

            ButtonType buttonTypeOK = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOK);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeOK) {
                	Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("LoginManager.fxml"));
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

    @FXML
    public void forgotPassword(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("forgotPasswordManager.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
}

