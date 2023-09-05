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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Subject;
import model.Teacher;
import model.Teacher_Subject;
import model.TeacherAccount;
import repository.SubjectRepositoryImpl;
import repository.TeacherAccountRepositoryImpl;
import repository.TeacherRepositoryImpl;
import service.ManagerServiceImpl;
import service.TeacherServiceImpl;

public class ManagerTeacherSubjectController implements Initializable {

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSearch;


    @FXML
    private TableColumn<Subject, String> colSubjectID;

    @FXML
    private TableColumn<Subject, String> colSubjectName;
    
    @FXML
    private TableColumn<Subject, Integer> colCredit;

    @FXML
    private TableView<Subject> tableTeacherSubject;

    @FXML
    private TextField textFilter;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtSemester;

    @FXML
    private TextField txtSubjectID;

    @FXML
    private TextField txtSubjectName;

    @FXML
    private TextField txtTeacherID;
    Integer index;
    @FXML
    void getItem(MouseEvent event) {
    	index = tableTeacherSubject.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		txtSubjectID.setText(colSubjectID.getCellData(index).toString());
		txtSubjectName.setText(colSubjectName.getCellData(index).toString());
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource() == btnAdd)
		{
    		add();
		}
    	else if(event.getSource() == btnSearch)
		{
    		filterSearch();
		}
    }
    public void filterSearch(){
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
    	colSubjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
    	colCredit.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
		Subject s = SubjectRepositoryImpl.getInstance().findById(textFilter.getText());
		System.out.print(s.getName());
		ObservableList<Subject> product = FXCollections.observableArrayList();
		product.add(new Subject(s.getSubjectId(), s.getName(), s.getCredits()));
		tableTeacherSubject.setItems(product);
	   }
    public void add()
    {
    	Teacher teacher = TeacherRepositoryImpl.getInstance().findById(txtTeacherID.getText());
    	Subject subject = ManagerServiceImpl.getInstance().findSubject(txtSubjectID.getText());
    	Teacher_Subject ts = new Teacher_Subject(teacher, subject, txtSemester.getText(), txtClass.getText());
    	teacher.getTeacher_subject().add(ts);
    	//String passWord = ManagerServiceImpl.getInstance().findTeacher(txtTeacherID.getText()).getAccount().getPassword();
    	TeacherRepositoryImpl.getInstance().save(teacher);
    	//TeacherAccount tc = new TeacherAccount(teacher, passWord);
    	//TeacherAccountRepositoryImpl.getInstance().save(tc);
    }
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	showTeacherSubject();
	}
    public void showTeacherSubject()
    {
    	colSubjectID.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
    	colSubjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
    	colCredit.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
    	tableTeacherSubject.setItems(getProductList());
    }
    ObservableList<Subject> getProductList() {
		ObservableList<Subject> product = FXCollections.observableArrayList();
		List<Subject> list = SubjectRepositoryImpl.getInstance().findAll();
		for (Subject s : list) {
			product.add(new Subject(s.getSubjectId(), s.getName(), s.getCredits()));
		}
		return product;
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
