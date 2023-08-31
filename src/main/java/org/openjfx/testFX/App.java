package org.openjfx.testFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;
import model.Teacher;
import model.Manager;
import model.Subject;
import model.ManagerAccount;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import repository.StudentRepositoryImpl;
import repository.SubjectRepositoryImpl;
import repository.TeacherRepositoryImpl;
import repository.ManagerAccountRepositoryImpl;
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
        scene = new Scene(loadFXML("menuHomeTeacher"), 1000, 600);
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
		/*
		 * List<Student> list = ManagerServiceImpl.getInstance().viewStudent(); for
		 * (Student s : list) { System.out.print(s.getStudentId()); }
		 */
		/*
		 * Student student = new Student("21127121", "Phan", "Van Nguyen", true,
		 * "nguyenpv.30@gmail.com"); StudentRepositoryImpl.getInstance().save(student);
		 */
		/*
		 * Teacher teacher = new Teacher("TC001", "Tran", "Duy Quang",
		 * true,"tdquang1980@gmail.com"); Teacher teacher1 = new Teacher("TC002", "Bui",
		 * "Huy Thong", true,"BHThong1990@gmail.com");
		 * TeacherRepositoryImpl.getInstance().save(teacher1);
		 * TeacherRepositoryImpl.getInstance().save(teacher);
		 */
		/*
		 * Subject subject = new Subject("CS0001", "Nhap Mon lap Trinh", 4); Subject
		 * subject1 = new Subject("CS0002", "Toan Roi Rac", 4);
		 * SubjectRepositoryImpl.getInstance().save(subject);
		 * SubjectRepositoryImpl.getInstance().save(subject1);
		 */
    
		 launch();
    }

}