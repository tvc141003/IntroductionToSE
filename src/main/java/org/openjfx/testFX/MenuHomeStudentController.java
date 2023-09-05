package org.openjfx.testFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.StudentServiceImpl;
import javafx.scene.Node;
public class MenuHomeStudentController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void onClickToProfile(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("StudentInformation.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToListCourse(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("StudentListCourse.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToEnrollCourse(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("StudentEnrollCourse.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    public void onClickToSchedule(ActionEvent event)throws IOException {
    	LoginStudentController student =  new LoginStudentController();
		String studentID = student.getId();
		System.out.print(studentID);
		System.out.print(StudentServiceImpl.getInstance().findStudent(studentID).getStudentId());
    	Parent root = FXMLLoader.load(getClass().getResource("StudentSchedule.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void onClickToLogOut(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

