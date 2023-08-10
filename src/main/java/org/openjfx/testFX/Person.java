package org.openjfx.testFX;

import java.awt.Checkbox;

import javafx.scene.control.CheckBox;

public class Person {
    private String name;
    private String ID;
    private String Teacher;
    private String Schedule;
    private CheckBox Select;
     public Person(String name, String ID, String Teacher, String Schedule, CheckBox Select) {
        this.name = name;
        this.ID = ID;
        this.Teacher = Teacher;
        this.Schedule = Schedule;
        this.Select = Select;
    }
     public String getName() {
        return name;
    }
     public String getID() {
        return ID;
    }
    public String getTeacher() {
    	return Teacher;
    }
    public String getSchedule() {
    	return Schedule;
    }
	public CheckBox getSeclect() {
    	return Select;
    }
}