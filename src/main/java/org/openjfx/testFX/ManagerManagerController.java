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
import repository.ManagerRepositoryImpl;
import service.ManagerServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagerManagerController implements Initializable {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnAddManager;

    @FXML
    private Button btnDeleteManager;

    @FXML
    private Button btnUpdateManager;

    @FXML
    private Button btnSearch;
    
    @FXML
    private TableColumn<Manager, String> colEmail;

    @FXML
    private TableColumn<Manager, String> colFirstName;

    @FXML
    private TableColumn<Manager, String> colGender;

    @FXML
    private TableColumn<Manager, String> colLastName;

    @FXML
    private TableColumn<Manager, String> colManagerId;

    @FXML
    private RadioButton rdMale;

    @FXML
    private TableView<Manager> tableManager;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtManagerID;
    
    @FXML
    private TextField textFilter;
    
    
    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showManager();
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
	public void showManager()
	{
		colManagerId.setCellValueFactory(new PropertyValueFactory<Manager, String>("managerId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Manager, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Manager, String>("lastName"));
		colGender.setCellValueFactory(new PropertyValueFactory<Manager, String>("gender"));
		colGender.setCellValueFactory(cellData -> {
            boolean isMale = cellData.getValue().isGender();
            String gender = isMale ? "Male" : "Female";
            return new SimpleStringProperty(gender);
        });
		colEmail.setCellValueFactory(new PropertyValueFactory<Manager, String>("email"));
		tableManager.setItems(getManagerList());
	}
	ObservableList<Manager> getManagerList() {
		ObservableList<Manager> managerList = FXCollections.observableArrayList();
		List<Manager> list = ManagerServiceImpl.getInstance().viewManager();
		for (Manager s : list) {
			managerList.add(new Manager(s.getManagerId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
		}
		return managerList;
	}
    Integer index;
    
    @FXML
    void getItem(MouseEvent event) {
    	index = tableManager.getSelectionModel().getSelectedIndex();
		if(index <= -1)
		{
			return;
		}
		txtManagerID.setText(colManagerId.getCellData(index).toString());
		txtFirstName.setText(colFirstName.getCellData(index).toString());
		txtLastName.setText(colLastName.getCellData(index).toString());
		txtEmail.setText(colEmail.getCellData(index).toString());
		tableManager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
    	if(event.getSource() == btnAddManager)
		{
			addManager();
			showManager();
		}
		else if(event.getSource() == btnUpdateManager)
		{
			updateManager();
			showManager();
		}
		if(event.getSource() == btnDeleteManager)
		{
			deleteManager();
			showManager();
		}
		if(event.getSource() == btnSearch)
		{
			if(textFilter.getText() == "")
			 {
				 showManager();
			 }
			else
			{
				filterSearch();
			}
		}
	}
	public void addManager()
	{
		/*
		 * Manager manager = new Manager(txtManagerID.getText(), txtFirstName.getText(),
		 * txtLastName.getText(), true, txtEmail.getText());
		 * ManagerRepositoryImpl.getInstance().save(manager);
		 */
		Manager manager = new Manager(txtManagerID.getText(), txtFirstName.getText(),txtLastName.getText(), gender(), txtEmail.getText());
		ManagerServiceImpl.getInstance().createManager(manager);
	}
	public void updateManager()
	{
		
	}
	public void deleteManager()
	{
		ManagerRepositoryImpl.getInstance().remove(txtManagerID.getText());
	}

	@FXML
	public void onClickToBackHomeManager(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuHomeManager.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void filterSearch() {
			  colManagerId.setCellValueFactory(new PropertyValueFactory<Manager, String>("managerId"));
				colFirstName.setCellValueFactory(new PropertyValueFactory<Manager, String>("firstName"));
				colLastName.setCellValueFactory(new PropertyValueFactory<Manager, String>("lastName"));
				colGender.setCellValueFactory(new PropertyValueFactory<Manager, String>("gender"));
				colGender.setCellValueFactory(cellData -> {
		            boolean isMale = cellData.getValue().isGender();
		            String gender = isMale ? "Male" : "Female";
		            return new SimpleStringProperty(gender);
		        });
				colEmail.setCellValueFactory(new PropertyValueFactory<Manager, String>("email"));
				Manager s = ManagerServiceImpl.getInstance().findManager(textFilter.getText());
				ObservableList<Manager> product = FXCollections.observableArrayList();
				product.add(new Manager(s.getManagerId(), s.getFirstName(), s.getLastName(), s.isGender(), s.getEmail()));
				tableManager.setItems(product);
	   }

}
