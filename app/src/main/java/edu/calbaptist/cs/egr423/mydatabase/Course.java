package edu.calbaptist.cs.egr423.mydatabase;

/**
 * Created by AaronR on 10/6/17.
 */

public class Course {
    private long id;
    private String name;
    private String courseCode;
    private int units;

    public Course() {
    }

    public Course(long id, String name, String courseCode, int units) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.units = units;
    }

    @Override
    public String toString(){
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnits(int units) {
        this.units = units;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getUnits() {
        return units;
    }
}
