package org.openjfx.testFX;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class ManagerSubjectController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToAddSubject(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerSubjectAdd.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToUpdateSubject(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerSubjectUpdate.fxml"));
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

