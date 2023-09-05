package org.openjfx.testFX;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Student;
import model.Student_Subject;
import model.Subject;
import repository.StudentRepositoryImpl;
import repository.SubjectRepositoryImpl;
import service.StudentServiceImpl;

public class StudentListCourseController implements Initializable{

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btn;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Student_Subject, String> colClass;

    @FXML
    private TableColumn<Student_Subject, String> colSemester;

    @FXML
    private TableColumn<Student_Subject, Subject> colSubjectID;

    @FXML
    private TableView<Student_Subject> tableTeacherSubject;

    @FXML
    private TextField textFilter;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtSemester;

    @FXML
    private TextField txtSubjectID;

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
		txtSubjectID.setText( tableTeacherSubject.getItems().get(index).getSubject().getSubjectId());
		txtCourseName.setText( tableTeacherSubject.getItems().get(index).getSubject().getName());
		txtSemester.setText(colSemester.getCellData(index).toString());
		txtClass.setText(colClass.getCellData(index).toString());
    }


    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btn)
		{
    		add();
    		showTeacherSubject();
		}
    }
    public void add()
    {
    	LoginStudentController student = new LoginStudentController();
    	String Id = student.getId();
    	Student s = StudentServiceImpl.getInstance().findStudent(Id);
    	Subject subject = SubjectRepositoryImpl.getInstance().findById(txtSubjectID.getText());
    	Student_Subject ss = new Student_Subject(s, subject, txtSemester.getText(), txtClass.getText());
    	StudentRepositoryImpl.getInstance().removeStudent_Subject(s, ss);
    }
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	showTeacherSubject();
	}
    public void showTeacherSubject()
    {
    	//colSubjectID.setCellValueFactory(new PropertyValueFactory<Teacher_Subject, Subject>("subject_id"));
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<Student_Subject, Subject>("subject"));
    	colSemester.setCellValueFactory(new PropertyValueFactory<Student_Subject, String>("semester"));
    	colClass.setCellValueFactory(new PropertyValueFactory<Student_Subject, String>("className"));
    	tableTeacherSubject.setItems(getProductList());
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<>("subject"));
    	colSubjectID.setCellFactory(column -> {
    	    return new TableCell<Student_Subject, Subject>() {
    	        @Override
    	        protected void updateItem(Subject item, boolean empty) {
    	            super.updateItem(item, empty);
    	            if (item == null || empty) {
    	                setText(null);
    	            } else {
    	                setText(item.getName()); // Assuming there is a method to retrieve the subject name
    	            }
    	        }
    	    };
    	});
    }
    ObservableList<Student_Subject> getProductList() {
    	LoginStudentController student = new LoginStudentController();
    	String Id = student.getId();
		ObservableList<Student_Subject> product = FXCollections.observableArrayList();
		Student list = StudentRepositoryImpl.getInstance().findById(Id);
		for (Student_Subject s : list.getStudent_subject()) {
				product.add(new Student_Subject(s.getStudent(),s.getSubject(),  s.getSemester(), s.getClassName()));
		}
		return product;
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
