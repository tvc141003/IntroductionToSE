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
import service.TeacherServiceImpl;

public class ForgotPasswordTeacherController {

    @FXML
    private TextField email;
    @FXML
    private TextField Username;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void sendMail(ActionEvent event) throws IOException{
    	TeacherServiceImpl.getInstance().forgotPassword(Username.getText(), email.getText());
    	Parent root = FXMLLoader.load(getClass().getResource("LoginTeacher.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}
