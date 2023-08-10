package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
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
	private CheckBox cbGenderMale;

	@FXML
	private CheckBox cbxGenderFemale;

	@FXML
	private TableColumn<Student, String> colEmail;

	@FXML
	private TableColumn<Student, String> colFirstName;

	@FXML
	private TableColumn<Student, Boolean> colGender;

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
	private TableView<Student> tableStudent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// List<Student> list = getStudentList();
		showStudent();
	}
	public void showStudent()
	{
		colStudentId.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Student, Boolean>("gender"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		tableStudent.setItems(getProductList());
	}
	ObservableList<Student> getProductList() {
		ObservableList<Student> product = FXCollections.observableArrayList();
		List<Student> list = ManagerServiceImpl.getInstance().viewStudent();
		for (Student s : list) {
			product.add(new Student(s.getStudentId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
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
		Student student = new Student(txtStudentID.getText(), txtFirstName.getText(), txtLastName.getText(), true, txtEmail.getText());

		StudentRepositoryImpl.getInstance().save(student);
	}
	public void updateStudent()
	{
		
	}
	public void deleteStudent()
	{
		StudentRepositoryImpl.getInstance().remove(txtStudentID.getText());
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
