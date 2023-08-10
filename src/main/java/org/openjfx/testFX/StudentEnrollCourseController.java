package org.openjfx.testFX;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.DirectoryChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentEnrollCourseController implements Initializable{
	private static final CheckBox True = null;
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> ID;
    @FXML
    private TableColumn<Person, String> teacher;
    @FXML
    private TableColumn<Person, String> Schedule;
    @FXML
    private TableColumn<Person, CheckBox> Select;
    
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
		Schedule.setCellValueFactory(new PropertyValueFactory<>("Schedule"));
		Select.setCellValueFactory(new PropertyValueFactory<>("select"));
		table.setItems(getProductList());
	}
	
	ObservableList<Person> getProductList() {
		ObservableList<Person> product = FXCollections.observableArrayList();
		product.add(new Person("CSC2003","DSA","Nguyen Ngoc Thao","30/4/2021",True));
		product.add(new Person("CSC20034","DSA1","Nguyen Ngoc Thao","30/4/2021",True));
		product.add(new Person("CSC2004","DSA","Nguyen Ngoc Thao","30/4/2021",True));
		product.add(new Person("CSC200345","DSA3","Nguyen Ngoc Thao","30/4/2021",True));
		return product;
	}
	@FXML
    public void onClickToBackHomeStudent(ActionEvent event)throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("menuHomeStudent.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}