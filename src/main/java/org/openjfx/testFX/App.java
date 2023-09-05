package org.openjfx.testFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Student;
import model.Student_Subject;
import model.Teacher;
import model.Teacher_Subject;
import model.Manager;
import model.Subject;
import model.ManagerAccount;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import repository.StudentAccountRepositoryImpl;
import repository.StudentRepositoryImpl;
import repository.SubjectRepositoryImpl;
import repository.TeacherRepositoryImpl;
import repository.ManagerAccountRepositoryImpl;
import service.ManagerServiceImpl;
import service.StudentServiceImpl;
import service.TeacherServiceImpl;
import utils.MailUtils;

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
        scene = new Scene(loadFXML("View"), 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
    	//MailUtils.getInstance().sendPassword("nguyenpv.30@gmail.com", "M001Phan");
    	//SubjectRepositoryImpl.getInstance().remove("CS0003");
    	//ManagerServiceImpl.getInstance().updateStudent("21127121", "Phan", "Nguyen", true, "nguyenpv.30@gmail.com", "21127121Phan");
    	//StudentAccountRepositoryImpl.getInstance().remove("21127021");
    	//StudentRepositoryImpl.getInstance().remove("21127021");
    	//StudentAccountRepositoryImpl.getInstance().isCorrect("21127121","21127121Phan");
    	//StudentServiceImpl.getInstance().login("21127121","21127121Phan");
    	/*Teacher s = TeacherRepositoryImpl.getInstance().findById("TC006");
    	System.out.print(s.getTeacherId());
    	Subject subject = SubjectRepositoryImpl.getInstance().findById("CSC10009");
    	System.out.print(subject.getSubjectId());
    	Teacher_Subject ss = new Teacher_Subject(s, subject, "HK2", "21CLC06");
    	TeacherRepositoryImpl.getInstance().removeTeacher_Subject(s, ss);*/
    	launch();
    }

}