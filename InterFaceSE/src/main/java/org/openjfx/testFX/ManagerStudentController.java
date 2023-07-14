package org.openjfx.testFX;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class ManagerStudentController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToAddStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerStudentAdd.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToUpdateStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerStudentUpdate.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToBackHomeManager(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

