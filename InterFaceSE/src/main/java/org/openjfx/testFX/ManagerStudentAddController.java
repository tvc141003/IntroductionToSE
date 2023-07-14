package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class ManagerStudentAddController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToBackManagerStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ManagerStudent.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

