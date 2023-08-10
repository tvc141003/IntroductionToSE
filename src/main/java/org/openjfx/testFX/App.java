package org.openjfx.testFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import service.ManagerServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 * @param <ManagerAccountReponsitoryImpl>
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ManagerStudent"), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Manager_Student_Add");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	List<Student> list = ManagerServiceImpl.getInstance().viewStudent();
		for (Student s : list) {
			System.out.print(s.getStudentId());
		}
        launch();
    }

}