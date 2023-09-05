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
import model.Teacher;
import model.Teacher_Subject;
import model.Subject;
import repository.TeacherRepositoryImpl;
import repository.SubjectRepositoryImpl;
import service.TeacherServiceImpl;

public class TeacherScheduleController implements Initializable{

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
		txtSubjectID.setText( tableTeacherSubject.getItems().get(index).getSubject_id().getSubjectId());
		txtCourseName.setText( tableTeacherSubject.getItems().get(index).getSubject_id().getName());
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
    	LoginTeacherController ID = new LoginTeacherController();
		String teacherID = ID.getUserName();
    	Teacher s = TeacherRepositoryImpl.getInstance().findById(teacherID);
    	System.out.print(s.getTeacherId());
    	Subject subject = SubjectRepositoryImpl.getInstance().findById(txtSubjectID.getText());
    	System.out.print(subject.getSubjectId());
    	Teacher_Subject ss = new Teacher_Subject(s, subject, txtSemester.getText() , txtClass.getText());
    	TeacherRepositoryImpl.getInstance().removeTeacher_Subject(s, ss);
    }
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
    	                setText(item.getName()); // Assuming there is a method to retrieve the subject name
    	            }
    	        }
    	    };
    	});
    }
    ObservableList<Teacher_Subject> getProductList() {
    	LoginTeacherController teacher = new LoginTeacherController();
    	String Id = teacher.getUserName();
		ObservableList<Teacher_Subject> product = FXCollections.observableArrayList();
		Teacher list = TeacherRepositoryImpl.getInstance().findById(Id);
		for (Teacher_Subject s : list.getTeacher_subject()) {
				product.add(new Teacher_Subject(s.getTeacher(),s.getSubject_id(),  s.getSemester(), s.getClassName()));
		}
		return product;
	}
    @FXML
    void onClickToBackHomeManager(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeTeacher.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
