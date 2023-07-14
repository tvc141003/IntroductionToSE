package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class MenuHomeManagerController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerStudent.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToTeacher(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerTeacher.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToSubject(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerSubject.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToManager(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerManager.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

