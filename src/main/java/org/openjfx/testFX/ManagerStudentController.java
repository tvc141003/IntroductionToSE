package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
import repository.StudentAccountRepositoryImpl;
import repository.StudentRepositoryImpl;
import service.ManagerServiceImpl;

public class ManagerStudentController implements Initializable {

	private static final boolean True = false;
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private Button btnAddStudent;

    @FXML
    private Button btnDeleteStudent;

    @FXML
    private Button btnUpdateStudent;
    
    @FXML
    private ToggleGroup gender;
    
    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

	@FXML
	private TableColumn<Student, String> colEmail;

	@FXML
	private TableColumn<Student, String> colFirstName;

	@FXML
	private TableColumn<Student, String> colGender;

	@FXML
	private TableColumn<Student, String> colLastName;

	@FXML
	private TableColumn<Student, String> colStudentId;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtStudentID;

	@FXML
    private TextField textFilter;
    
	  
	@FXML
	private TableView<Student> tableStudent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// List<Student> list = getStudentList();
		showStudent();
	}
	private boolean checkGender;
	public boolean getGender()
	{
		return checkGender;
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
	public void showStudent()
	{
		colStudentId.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
		colGender.setCellValueFactory(cellData -> {
            boolean isMale = cellData.getValue().isGender();
            String gender = isMale ? "Male" : "Female";
            return new SimpleStringProperty(gender);
        });
		colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		tableStudent.setItems(getProductList());
	}
	ObservableList<Student> getProductList() {
		ObservableList<Student> product = FXCollections.observableArrayList();
		List<Student> list = ManagerServiceImpl.getInstance().viewStudent();
		for (Student s : list) {
			product.add(new Student(s.getStudentId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
			System.out.print(s.isGender());
		}
		return product;
	}
	
	@FXML
	public void handleButtonAction(ActionEvent event) throws IOException {
		if(event.getSource() == btnAddStudent)
		{
			addStudent();
			showStudent();
		}
		else if(event.getSource() == btnUpdateStudent)
		{
			updateStudent();
			showStudent();
		}
		if(event.getSource() == btnDeleteStudent)
		{
			deleteStudent();
			showStudent();
		}
	}
	
	public void addStudent()
	{
		
		Student student = new Student(txtStudentID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText());
		//StudentRepositoryImpl.getInstance().save(student);
		ManagerServiceImpl.getInstance().createStudent(student);
	}
	public void updateStudent()
	{
		String passWord = ManagerServiceImpl.getInstance().findStudent(txtStudentID.getText()).getAccount().getPassword();
		ManagerServiceImpl.getInstance().updateStudent(txtStudentID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText(), passWord);
	}
	public void deleteStudent()
	{
		Student student = new Student(txtStudentID.getText(), txtFirstName.getText(), txtLastName.getText(), gender(), txtEmail.getText());
		StudentAccountRepositoryImpl.getInstance().remove(txtStudentID.getText());
		StudentRepositoryImpl.getInstance().remove(txtStudentID.getText());
		//ManagerServiceImpl.getInstance().deleteStudent(student);
	}
	Integer index;
	@FXML
    void getItem(MouseEvent event) {
		index = tableStudent.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		txtStudentID.setText(colStudentId.getCellData(index).toString());
		txtFirstName.setText(colFirstName.getCellData(index).toString());
		txtLastName.setText(colLastName.getCellData(index).toString());
		txtEmail.setText(colEmail.getCellData(index).toString());
		tableStudent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
	public void onClickToBackHomeManager(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	  @FXML
	 public void filterSearch(ActionEvent event) throws IOException {
		  if(textFilter.getText() == "")
		  {
			 showStudent();
		  }
		  else
		  {
			  colStudentId.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
				colFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
				colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
				colGender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
				colGender.setCellValueFactory(cellData -> {
		            boolean isMale = cellData.getValue().isGender();
		            String gender = isMale ? "Male" : "Female";
		            return new SimpleStringProperty(gender);
		        });
				colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
				Student s = ManagerServiceImpl.getInstance().findStudent(textFilter.getText());
				ObservableList<Student> product = FXCollections.observableArrayList();
				product.add(new Student(s.getStudentId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
				tableStudent.setItems(product);
		  }
	   }


}
