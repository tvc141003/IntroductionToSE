package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import service.ManagerServiceImpl;

public class ForgotPasswordController {

    @FXML
    private TextField email;
    @FXML
    private TextField Username;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void login(ActionEvent event) throws IOException{
    	ManagerServiceImpl.getInstance().forgotPassword(email.getText(), Username.getText());
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}
