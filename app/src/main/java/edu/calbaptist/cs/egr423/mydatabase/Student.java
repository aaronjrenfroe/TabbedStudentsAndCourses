package edu.calbaptist.cs.egr423.mydatabase;

public class Student {

    private long id;
    private String name;
    private String email;
    private String comment;

    public Student() {
    }

    public Student(long id, String name, String email, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }

    @Override
    public String toString() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
