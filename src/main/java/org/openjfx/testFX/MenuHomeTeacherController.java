package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Node;
public class MenuHomeTeacherController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToProfile(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("TeacherInformation.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToTeacherClass(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("TeacherClass.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToSchedule(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("TeacherSchedule.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void onClickToLogOut(ActionEvent event)throws IOException {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("Are you sure sign out?");
        alert.setContentText("Please select an option.");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
            	Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("View.fxml"));
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	            	scene = new Scene(root);
	            	stage.setScene(scene);
	            	stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (buttonType == buttonTypeNo) {
                System.out.println("No selected");
            }
        });
    }
}

