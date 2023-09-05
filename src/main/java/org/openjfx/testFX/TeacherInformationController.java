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
import repository.TeacherRepositoryImpl;
import service.ManagerServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TeacherInformationController implements Initializable {

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
    private TextField txtTeacherID;

    @FXML
    void onClickToBackStudentHome(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeTeacher.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btnUpdate)
		{
    		changeInformation();
    		showInformation();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showInformation();
		
	}
	public void showInformation()
	{
		LoginTeacherController ID = new LoginTeacherController();
		//String studentID = ID.getUserName();
		String teacherID = ID.getUserName();
		//s = StudentRepositoryImpl.getInstance().findById(studentID);
		txtTeacherID.setText(TeacherRepositoryImpl.getInstance().findById(teacherID).getId());
		txtFirstName.setText(TeacherRepositoryImpl.getInstance().findById(teacherID).getFirstName());
		txtLastName.setText(TeacherRepositoryImpl.getInstance().findById(teacherID).getLastName());
		txtEmail.setText(TeacherRepositoryImpl.getInstance().findById(teacherID).getEmail());
		boolean isMale = TeacherRepositoryImpl.getInstance().findById(teacherID).isGender();
        if (isMale) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
		txtPassword.setText(TeacherRepositoryImpl.getInstance().findById(teacherID).getAccount().getPassword());
	}
	public void changeInformation()
	{
		//LoginController ID = new LoginController();
		//String studentID = ID.getUserName();
		//String studentID = "21127121";
		//txtPassword.setText(StudentRepositoryImpl.getInstance().findById(studentID).getAccount().getPassword());
		ManagerServiceImpl.getInstance().updateTeacher(txtTeacherID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText(), txtPassword.getText() );
	}
	public boolean gender()
	{	
		boolean check = true;
		RadioButton selected = (RadioButton) gender.getSelectedToggle();
		
			 System.out.print(selected.getText());
			 if(selected.getText().equals("Male")) 
			 { 
				 check = true;
				 return check;
			 } 
			 else if(selected.getText().equals("Female")) {
				 check = false;
				 return check;
		}
		return check;
	}
}
