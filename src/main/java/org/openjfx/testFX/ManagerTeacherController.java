package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Manager;
import model.Student;
import model.Teacher;
import repository.StudentRepositoryImpl;
import repository.TeacherRepositoryImpl;
import service.ManagerServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagerTeacherController implements Initializable {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

    @FXML
    private Button btnAddTeacher;

    @FXML
    private Button btnDeleteTeacher;

    @FXML
    private Button btnUpdateTeacher;
    
    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Teacher, String> colEmail;

    @FXML
    private TableColumn<Teacher, String> colFirstName;

    @FXML
    private TableColumn<Teacher, String> colGender;

    @FXML
    private TableColumn<Teacher, String> colLastName;

    @FXML
    private TableColumn<Teacher, String> colTeacherId;


    @FXML
    private TableView<Teacher> tableTeacher;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtTeacherID;
    
    @FXML
    private TextField textFilter;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showTeacher();
	}
	public void showTeacher()
	{
		colTeacherId.setCellValueFactory(new PropertyValueFactory<Teacher, String>("teacherId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));
		colGender.setCellValueFactory(cellData -> {
            boolean isMale = cellData.getValue().isGender();
            String gender = isMale ? "Male" : "Female";
            return new SimpleStringProperty(gender);
        });
		tableTeacher.setItems(getTeacherList());
	}
	ObservableList<Teacher> getTeacherList() {
		ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
		List<Teacher> list = ManagerServiceImpl.getInstance().viewTeacher();
		for (Teacher s : list) {
			teacherList.add(new Teacher(s.getTeacherId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
		}
		return teacherList;
	}
    Integer index;
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
    @FXML
    void getItem(MouseEvent event) {
    	index = tableTeacher.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		txtTeacherID.setText(colTeacherId.getCellData(index).toString());
		txtFirstName.setText(colFirstName.getCellData(index).toString());
		txtLastName.setText(colLastName.getCellData(index).toString());
		txtEmail.setText(colEmail.getCellData(index).toString());
		tableTeacher.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                boolean isMale = newSelection.isGender();
                if (isMale) {
                	rbMale.setSelected(true);
                } else {
                    rbFemale.setSelected(true);
                }
            }
        });
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btnAddTeacher)
		{
			addTeacher();
			showTeacher();
		}
		else if(event.getSource() == btnUpdateTeacher)
		{
			updateTeacher();
			showTeacher();
		}
		if(event.getSource() == btnDeleteTeacher)
		{
			deleteTeacher();
			showTeacher();
		}
		if(event.getSource() == btnSearch)
		{
			filterSearch();
		}
	}
    public void filterSearch() {
    	colTeacherId.setCellValueFactory(new PropertyValueFactory<Teacher, String>("teacherId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("lastName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));
		colGender.setCellValueFactory(cellData -> {
            boolean isMale = cellData.getValue().isGender();
            String gender = isMale ? "Male" : "Female";
            return new SimpleStringProperty(gender);
        });
			Teacher s = ManagerServiceImpl.getInstance().findTeacher(textFilter.getText());
			ObservableList<Teacher> product = FXCollections.observableArrayList();
			product.add(new Teacher(s.getTeacherId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
			tableTeacher.setItems(product);
 }

	public void addTeacher()
	{
		Teacher teacher = new Teacher(txtTeacherID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText());
		ManagerServiceImpl.getInstance().createTeacher(teacher);
		//ManagerServiceImpl.getInstance().createTeacher(teacher);
	}
	public void updateTeacher()
	{
		String passWord = ManagerServiceImpl.getInstance().findTeacher(txtTeacherID.getText()).getAccount().getPassword();
		ManagerServiceImpl.getInstance().updateTeacher(txtTeacherID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText(), passWord);
	}
	public void deleteTeacher()
	{
		Teacher teacher = new Teacher(txtTeacherID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText());
		ManagerServiceImpl.getInstance().deleteTeacher(teacher);
	}

	@FXML
	public void onClickToBackHomeManager(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    

}
