package org.openjfx.testFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Student;
import repository.StudentRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StudentInformationController implements Initializable {

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnUpdate;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtStudentID;

    @FXML
    void onClickToBackStudentHome(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeStudent.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btnUpdate)
		{
    		
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showInformation();
		
	}
	public void showInformation()
	{
		//LoginController ID = new LoginController();
		//String studentID = ID.getUserName();
		String studentID = "21127121";
		//s = StudentRepositoryImpl.getInstance().findById(studentID);
		txtStudentID.setText(StudentRepositoryImpl.getInstance().findById(studentID).getStudentId());
		txtFirstName.setText(StudentRepositoryImpl.getInstance().findById(studentID).getFirstName());
		txtLastName.setText(StudentRepositoryImpl.getInstance().findById(studentID).getLastName());
		txtEmail.setText(StudentRepositoryImpl.getInstance().findById(studentID).getEmail());
		boolean isMale = StudentRepositoryImpl.getInstance().findById(studentID).isGender();
        if (isMale) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
		//txtPassword.setText(StudentRepositoryImpl.getInstance().findById(studentID).getAccount().getPassword());
	}
	public void changeInformation()
	{
		//LoginController ID = new LoginController();
		//String studentID = ID.getUserName();
		//String studentID = "21127121";
		//txtPassword.setText(StudentRepositoryImpl.getInstance().findById(studentID).getAccount().getPassword());
	}

}
