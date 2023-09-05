package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

public class ViewController {

    @FXML
    private Button btnManager;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnTeacher;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void hannleAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btnManager)
		{
    		Parent root = FXMLLoader.load(getClass().getResource("LoginManager.fxml"));
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
		}
    	else if(event.getSource() == btnStudent)
		{
    		Parent root = FXMLLoader.load(getClass().getResource("LoginStudent.fxml"));
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
		}
    	else if(event.getSource() == btnTeacher)
		{
    		Parent root = FXMLLoader.load(getClass().getResource("LoginTeacher.fxml"));
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
		}
    }

}
