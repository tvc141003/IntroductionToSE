package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import model.Manager;
import model.Student;
import service.ManagerServiceImpl;
import service.StudentServiceImpl;

public class ManagerInformationController implements Initializable {
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
    private TextField txtManagerID;

    @FXML
    private TextField txtPassword;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	ShowManager();
		
	}
    public void ShowManager()
    {
    	LoginManagerController manager = new LoginManagerController();
    	String Id = manager.getId();
    	Manager s = ManagerServiceImpl.getInstance().findManager(Id);
    	txtManagerID.setText(s.getManagerId());
    	txtFirstName.setText(s.getFirstName());
    	txtLastName.setText(s.getLastName());
    	boolean isMale = s.isGender();
        if (isMale) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
        txtEmail.setText(s.getEmail());
        txtPassword.setText(s.getAccount().getPassword());
    }
    
    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btnUpdate)
		{
    		changeInformation();
    		ShowManager();
		}
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
    public void changeInformation()
    {
    	ManagerServiceImpl.getInstance().updateManager(txtManagerID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText(), txtPassword.getText() );
    }

    @FXML
    void onClickToBackManagertHome(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

}
