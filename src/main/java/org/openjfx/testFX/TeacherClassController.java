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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.Student;
import model.Subject;
import model.Teacher;
import model.Teacher_Subject;
import repository.TeacherRepositoryImpl;
import service.ManagerServiceImpl;

public class TeacherClassController implements Initializable {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TableColumn<Subject, String> colCourseID;

    @FXML
    private TableColumn<Subject, String> colNameCourse;

    @FXML
    private TableView<Subject> tableClass;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showInformation();
		
	}
	public void showInformation() 
	{
		colCourseID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
		colNameCourse.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
		tableClass.setItems(getProductList());
	}
	ObservableList<Subject> getProductList() {
		ObservableList<Subject> product = FXCollections.observableArrayList();
		LoginTeacherController ID = new LoginTeacherController();
		String teacherID = ID.getUserName();
		Teacher teacher = TeacherRepositoryImpl.getInstance().findById(teacherID);
		System.out.print(teacher.getTeacherId());
		//List<Subject> list = ManagerServiceImpl.getInstance().viewStudent();
		for (Teacher_Subject s : teacher.getTeacher_subject()) {
			product.add(new Subject(s.getSubject_id().getSubjectId(), s.getSubject_id().getName(), s.getSubject_id().getCredits()));
			System.out.print(s.getSubject_id().getSubjectId());
		}
		return product;
	}
    
    @FXML
    public void onClickToBackHomeStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeTeacher.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

