package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
import model.Subject;
import model.Teacher;
import model.Teacher_Subject;
import repository.StudentRepositoryImpl;
import repository.SubjectRepositoryImpl;
import service.ManagerServiceImpl;
import service.TeacherServiceImpl;

public class ManagerSubjectController implements Initializable{

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button btnAddSubject;

    @FXML
    private Button btnDeleteSubject;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateSubject;

    @FXML
    private TableColumn<Subject, String> colCourseID;

    @FXML
    private TableColumn<Subject, String> colCourseName;

    @FXML
    private TableColumn<Subject, Integer> colCredits;

    @FXML
    private TableView<Subject> tableSubject;

    @FXML
    private TextField txtCourseID;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCredits;

    @FXML
    private TextField txtFilter;

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// List<Student> list = getStudentList();
    	showSubject();
	}
    public void showSubject()
	{
    	colCourseID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
    	colCourseName.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
    	colCredits.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
    	tableSubject.setItems(getProductList());
	}
	ObservableList<Subject> getProductList() {
		ObservableList<Subject> product = FXCollections.observableArrayList();
		List<Subject> list = SubjectRepositoryImpl.getInstance().findAll();
		for (Subject s : list) {
			product.add(new Subject(s.getSubjectId(), s.getName(), s.getCredits()));
		}
		return product;
	}
	Integer index;
    @FXML
    void getItem(MouseEvent event) {
    	index = tableSubject.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		txtCourseID.setText(colCourseID.getCellData(index).toString());
		txtCourseName.setText(colCourseName.getCellData(index).toString());
		txtCredits.setText(colCredits.getCellData(index).toString());
    }

    @FXML
	public void handleButtonAction(ActionEvent event) throws IOException {
		if(event.getSource() == btnAddSubject)
		{
			addSubject();
			showSubject();
		}
		else if(event.getSource() == btnDeleteSubject)
		{
			deleteSubject();
			showSubject();
			
		}
		else if(event.getSource() == btnUpdateSubject)
		{
			updateSubject();
			showSubject();
		}
		if(event.getSource() == btnSearch)
		{
			filterSearch();
		}
	}
    public void addSubject()
	{
		
		Subject subject = new Subject(txtCourseID.getText(), txtCourseName.getText(), Integer.parseInt(txtCredits.getText()));
		SubjectRepositoryImpl.getInstance().save(subject);
	}
	public void updateSubject()
	{
		ManagerServiceImpl.getInstance().updateSubject(txtCourseID.getText(), txtCourseName.getText(), Integer.parseInt(txtCredits.getText()));
	}
	public void deleteSubject()
	{
		Subject subject = new Subject(txtCourseID.getText(), txtCourseName.getText(), Integer.parseInt(txtCredits.getText()));
		ManagerServiceImpl.getInstance().deleteSubject(subject);
	}
	public void filterSearch(){
		colCourseID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
    	colCourseName.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
    	colCredits.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
		Subject s = SubjectRepositoryImpl.getInstance().findById(txtFilter.getText());
		System.out.print(s.getName());
		ObservableList<Subject> product = FXCollections.observableArrayList();
		product.add(new Subject(s.getSubjectId(), s.getName(), s.getCredits()));
		tableSubject.setItems(product);
	   }
	
    @FXML
    void onClickToBackHomeManager(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
