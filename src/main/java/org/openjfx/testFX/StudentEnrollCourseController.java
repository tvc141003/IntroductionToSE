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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
import model.StudentAccount;
import model.Subject;
import model.Teacher;
import model.TeacherAccount;
import model.Teacher_Subject;
import model.Student_Subject;
import repository.StudentAccountRepositoryImpl;
import repository.StudentRepositoryImpl;
import repository.SubjectRepositoryImpl;
import repository.TeacherAccountRepositoryImpl;
import repository.TeacherRepositoryImpl;
import service.ManagerServiceImpl;

public class StudentEnrollCourseController implements Initializable {

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btn;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Teacher_Subject, String> colClass;

    @FXML
    private TableColumn<Teacher_Subject, String> colSemester;

    @FXML
    private TableColumn<Teacher_Subject, Subject> colSubjectID;
    
    @FXML
    private TableView<Teacher_Subject> tableTeacherSubject;

    @FXML
    private TextField txtCourseName;
    
    @FXML
    private TextField textFilter;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtSemester;

    @FXML
    private TextField txtSubjectID;

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	showTeacherSubject();
	}
    public void showTeacherSubject()
    {
    	//colSubjectID.setCellValueFactory(new PropertyValueFactory<Teacher_Subject, Subject>("subject_id"));
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<Teacher_Subject, Subject>("subject_id"));
    	colSemester.setCellValueFactory(new PropertyValueFactory<Teacher_Subject, String>("semester"));
    	colClass.setCellValueFactory(new PropertyValueFactory<Teacher_Subject, String>("className"));
    	tableTeacherSubject.setItems(getProductList());
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
    	colSubjectID.setCellFactory(column -> {
    	    return new TableCell<Teacher_Subject, Subject>() {
    	        @Override
    	        protected void updateItem(Subject item, boolean empty) {
    	            super.updateItem(item, empty);
    	            if (item == null || empty) {
    	                setText(null);
    	            } else {
    	                setText(item.getSubjectId()); // Assuming there is a method to retrieve the subject name
    	            }
    	        }
    	    };
    	});
    }
    ObservableList<Teacher_Subject> getProductList() {
		ObservableList<Teacher_Subject> product = FXCollections.observableArrayList();
		List<Teacher> list = TeacherRepositoryImpl.getInstance().findAll();
		for (Teacher s : list) {
			for (Teacher_Subject ts : s.getTeacher_subject())
				product.add(new Teacher_Subject(ts.getTeacher(),ts.getSubject_id(),  ts.getSemester(), ts.getClassName()));
		}
		return product;
	}
    
    Integer index;
    @FXML
    void getItem(MouseEvent event) {
    	index = tableTeacherSubject.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		//Subject subject = tableTeacherSubject.getItems().get(index).getSubject_id().getName();
		//String subjectIdString = String.valueOf(subject);
		txtCourseName.setText( tableTeacherSubject.getItems().get(index).getSubject_id().getName());
		txtSubjectID.setText( tableTeacherSubject.getItems().get(index).getSubject_id().getSubjectId());
		txtSemester.setText(colSemester.getCellData(index).toString());
		txtClass.setText(colClass.getCellData(index).toString());
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btn)
		{
    		add();
		}
    }
    public void add()
    {
    	LoginStudentController student = new LoginStudentController();
    	String Id = student.getId();
    	Student s = StudentRepositoryImpl.getInstance().findById(Id);
    	Subject subject = ManagerServiceImpl.getInstance().findSubject(txtSubjectID.getText());
    	Student_Subject ts = new Student_Subject(s, subject, txtSemester.getText(), txtClass.getText());
    	s.getStudent_subject().add(ts);
    	StudentRepositoryImpl.getInstance().save(s);
    }
    @FXML
    void onClickToBackHomeManager(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeStudent.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
